package com.example.test.dtos;

import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class SavedUser {

    private String id;
    private LocalDate created;
    private LocalDate lastLogin;
    private String token;
    private boolean isActive;

    // Constructor
    public SavedUser(String id, LocalDate created, LocalDate lastLogin, String token, boolean isActive) {
        this.id =id;
        this.created = created;
        this.lastLogin = lastLogin;
        this.token = token;
        this.isActive = isActive;
    }

}
