package com.example.test.services;

import com.example.test.dtos.SavedUser;
import com.example.test.entities.Phone;
import com.example.test.entities.Userdata;
import com.example.test.exception.GlobalLogicException;
import com.example.test.repository.UserRepository;
import com.example.test.utils.JWTUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;



    @BeforeEach
    public void setup() throws NoSuchFieldException, IllegalAccessException {

        String newSecretKey = "newSecretKey";

        // Modifica el campo SECRET_KEY usando reflexión
        Field field = JWTUtil.class.getDeclaredField("SECRET_KEY");
        field.setAccessible(true);
        field.set(null, newSecretKey);

        userRepository = Mockito.mock(UserRepository.class);
        userService = new UserService(userRepository);


    }

    @Test
    public void testSignUpWithValidUser() {
        Userdata newUser = new Userdata();
        newUser.setEmail("test@example.com");
        newUser.setPassword("Password123");
        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.empty());
        when(userRepository.save(any(Userdata.class))).thenAnswer(invocation -> {
            Userdata savedUser = invocation.getArgument(0);
            savedUser.setId(UUID.randomUUID().toString());
            savedUser.setCreated(LocalDate.now());
            return savedUser;
        });

        SavedUser savedUser = userService.signUp(newUser);

        assertNotNull(savedUser.getId());
    }

    @Test
    public void testSignUpWithValidUserPhones() {
        Userdata newUser = new Userdata();
        newUser.setEmail("test@example.com");
        newUser.setPassword("Password123");
        List<Phone> phones=new ArrayList<>();
        Phone phone=new Phone();
        phone.setNumber("2342");
        phones.add(phone);
        newUser.setPhones(phones);
        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.empty());
        when(userRepository.save(any(Userdata.class))).thenAnswer(invocation -> {
            Userdata savedUser = invocation.getArgument(0);
            savedUser.setId(UUID.randomUUID().toString());
            savedUser.setCreated(LocalDate.now());
            return savedUser;
        });

        SavedUser savedUser = userService.signUp(newUser);

        assertNotNull(savedUser.getId());
    }

    @Test
    public void testSignUpWithInvalidEmail() {
        Userdata newUser = new Userdata();
        newUser.setEmail("invalid-email");
        assertThrows(GlobalLogicException.class, () -> userService.signUp(newUser));
    }

    @Test
    public void testSignUpWithInvalidPassword() {
        Userdata newUser = new Userdata();
        newUser.setEmail("test@example.com");
        newUser.setPassword("invalid");
        assertThrows(GlobalLogicException.class, () -> userService.signUp(newUser));
    }

    @Test
    public void testSignUpWithExistingUser() {
        Userdata existingUser = new Userdata();
        existingUser.setEmail("existing@example.com");
        when(userRepository.findByEmail("existing@example.com")).thenReturn(Optional.of(existingUser));

        Userdata newUser = new Userdata();
        newUser.setPassword("pass");
        newUser.setEmail("existing@example.com");
        assertThrows(GlobalLogicException.class, () -> userService.signUp(newUser));
    }

    @Test
    public void testSignUpFailsToSave() {
        Userdata newUser = new Userdata();
        newUser.setEmail("test@example.com");
        newUser.setPassword("Password123");
        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.empty());
        when(userRepository.save(any(Userdata.class))).thenReturn(null);

        assertThrows(GlobalLogicException.class, () -> userService.signUp(newUser));
    }

    @Test
    public void testLoginWithValidToken() {
        String token = JWTUtil.generateToken("uuid");

        Userdata userData = new Userdata();
        when(userRepository.getById(any(String.class))).thenReturn(userData);
        when(userRepository.save(any(Userdata.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Userdata loggedInUser = userService.login(token);
        assertNotNull(loggedInUser.getLastLogin());
        // ... Otras comprobaciones si es necesario
    }

    @Test
    public void testLoginWithExpiredToken() {
        String expiredToken = "expiredToken";
        when(userRepository.getById(any(String.class))).thenReturn(null);
        assertThrows(GlobalLogicException.class, () -> userService.login(expiredToken));
    }

    @Test
    public void testLoginWithInvalidToken() {
        String invalidToken = "invalidToken";
        when(userRepository.getById(any(String.class))).thenReturn(null);
        assertThrows(GlobalLogicException.class, () -> userService.login(invalidToken));
    }
}
