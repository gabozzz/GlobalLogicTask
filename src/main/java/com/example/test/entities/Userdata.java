package com.example.test.entities;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "USERDATA")
@Data
public class Userdata {
    @Id
    private String id;
    private String name;
    private String email;
    private String password;
    private LocalDate created;
    private LocalDate lastLogin;
    private String token;
    private boolean isActive;

    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Phone> phones;
}
