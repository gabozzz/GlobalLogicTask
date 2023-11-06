package com.example.test.repository;

import com.example.test.entities.Userdata;
import com.example.test.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@DataJpaTest
public class UserRepositoryTest {

    @MockBean
    private UserRepository userRepository;

    @BeforeEach
    public void setup() {
        userRepository = Mockito.mock(UserRepository.class);
    }

    @Test
    public void testFindByEmailWithExistingUser() {
        Userdata existingUser = new Userdata();
        existingUser.setEmail("test@example.com");
        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.of(existingUser));

        Optional<Userdata> foundUser = userRepository.findByEmail("test@example.com");

        assertTrue(foundUser.isPresent());
        assertEquals("test@example.com", foundUser.get().getEmail());
    }

    @Test
    public void testFindByEmailWithNonExistingUser() {
        when(userRepository.findByEmail("nonexisting@example.com")).thenReturn(Optional.empty());

        Optional<Userdata> foundUser = userRepository.findByEmail("nonexisting@example.com");

        assertFalse(foundUser.isPresent());
    }

    @Test
    public void testGetUserById() {
        Userdata user = new Userdata();
        user.setId("1");
        when(userRepository.getById("1")).thenReturn(user);

        Userdata foundUser = userRepository.getById("1");

        assertNotNull(foundUser);
        assertEquals("1", foundUser.getId());
    }

    // Otros métodos de prueba según la lógica de tu repositorio UserRepository
}
