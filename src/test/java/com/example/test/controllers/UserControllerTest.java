package com.example.test.controllers;

import com.example.test.dtos.SavedUser;
import com.example.test.entities.Userdata;
import com.example.test.exception.GlobalLogicException;
import com.example.test.services.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

public class UserControllerTest {

    UserService service = Mockito.mock(UserService.class) ;
    private UserController userController = new UserController(service);

    @Test
    public void signUp(){
        SavedUser save = new SavedUser("1", LocalDate.now(),
                LocalDate.now(),"3",true);
        Mockito.when(service.signUp(Mockito.any())).thenReturn(save);
        ResponseEntity<SavedUser> res = userController.signUp(new Userdata());
        Assertions.assertEquals( res.getStatusCode(),HttpStatus.CREATED);
    }

    @Test
    public void signUpError(){
        Mockito.when(service.signUp(Mockito.any())).thenThrow( new GlobalLogicException("",400,
                "Error mail invalido", HttpStatus.BAD_REQUEST));
        GlobalLogicException ex = Assertions.assertThrows(GlobalLogicException.class, () -> {
            userController.signUp(new Userdata());
        });
        Assertions.assertEquals( ex.getHttpStatus(),HttpStatus.BAD_REQUEST);

    }

    @Test
    public void login(){
        Userdata user=new Userdata();
        Mockito.when(service.login(Mockito.any())).thenReturn(user);
        ResponseEntity<Userdata> res = userController.login("Token");
        Assertions.assertEquals( res.getStatusCode(),HttpStatus.CREATED);
    }


    @Test
    public void loginError(){
        Mockito.when(service.login(Mockito.any())).thenThrow( new GlobalLogicException("",400,
                "Error mail invalido", HttpStatus.BAD_REQUEST));
        GlobalLogicException ex = Assertions.assertThrows(GlobalLogicException.class, () -> {
            userController.login("token");
        });
        Assertions.assertEquals( ex.getHttpStatus(),HttpStatus.BAD_REQUEST);

    }
}


