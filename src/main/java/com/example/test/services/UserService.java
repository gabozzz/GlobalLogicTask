package com.example.test.services;

import com.example.test.dtos.SavedUser;
import com.example.test.entities.Userdata;
import com.example.test.exception.GlobalLogicException;
import com.example.test.repository.PhoneRepository;
import com.example.test.repository.UserRepository;
import com.example.test.utils.JWTUtil;
import com.example.test.utils.PasswordEncoder;
import com.example.test.utils.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PhoneRepository phoneRepository;


    public SavedUser signUp(Userdata newUser) {
        if (!ValidationUtil.isValidEmail(newUser.getEmail())) {
            // Manejo de error para correo inválido
            throw  new GlobalLogicException("",400,
                    "Error mail invalido", HttpStatus.BAD_REQUEST);
        }

        if (!ValidationUtil.isValidPassword(newUser.getPassword())) {
            // Manejo de error para correo inválido
            throw  new GlobalLogicException("",400,
                    "Error password invalido", HttpStatus.BAD_REQUEST);
        }

         Optional<Userdata> existingUser = userRepository.findByEmail(newUser.getEmail());
        if (existingUser.isPresent()) {
             //Manejo de error para usuario existente -- este se puede quitar para evitar obtener
            // informacion de usuarios existentes
            throw  new GlobalLogicException("",400,
                    "Error usuario ya existente", HttpStatus.BAD_REQUEST);
        }

        // Lógica para generar token, establecer fechas, encriptar contraseña, etc.

        newUser.setPassword(PasswordEncoder.encryptPassword(newUser.getPassword()));
        newUser.setId(UUID.randomUUID().toString());
        newUser.setCreated(LocalDate.now());
        newUser.setActive(true);


        Userdata savedUser = updateUserInfo(newUser);

        if (savedUser==null) {
            throw  new GlobalLogicException("",400,
                    "Error al registrar el usuario",HttpStatus.BAD_REQUEST);
        }
        return new SavedUser(savedUser.getId(),savedUser.getCreated(),
                savedUser.getLastLogin(),savedUser.getToken(),savedUser.isActive());
    }

    private Userdata updateUserInfo (Userdata newUser) {
        newUser.setLastLogin(LocalDate.now());
        String token = JWTUtil.generateToken(String.valueOf(newUser.getId()));
        newUser.setToken(token);
        newUser.getPhones().parallelStream().forEach(phone -> phone.setUserId(newUser.getId())); // Establecer el userId en cada teléfono
        Userdata savedUser = userRepository.save(newUser);
        return savedUser;
    }


    public Userdata login(String token){
       String id =  JWTUtil.validateAndExtractUserId(token);
        if (id != null) {
            Userdata user = userRepository.getById(id);
            if (user != null) {
                return updateUserInfo(user);
            }
            else{
                throw  new GlobalLogicException("",400,
                        "Error, intenta volver a ingresar ",HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        else {

            throw  new GlobalLogicException("",400,
                    "Error tiempo de expiracion",HttpStatus.UNAUTHORIZED);
        }


    }


}
