package com.example.test.repository;

import com.example.test.entities.Phone;
import com.example.test.entities.Userdata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Long> {
    // Método para buscar un usuario por su email
    List<Phone> findAll();

}