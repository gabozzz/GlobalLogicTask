package com.example.test.controllers;

import com.example.test.dtos.SavedUser;
import com.example.test.entities.Userdata;
import com.example.test.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private UserService userService;



    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<SavedUser> signUp(@RequestBody Userdata newUser) {
        SavedUser savedUser = userService.signUp(newUser);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }


    @PostMapping("/login")
    public ResponseEntity<Userdata> login(@RequestHeader(name = "JWT") String jwt) {
        Userdata user = userService.login(jwt);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
}


