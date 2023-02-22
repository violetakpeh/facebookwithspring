package com.example.project7.repository;

import com.example.project7.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;
    private User user;

    @BeforeEach
    void setUp(){
        user = new User();
        user.setFirstName("Victor");
        user.setLastName("Daniels");
        user.setGender("male");
        user.setPassword("12345");
        user.setEmailAddress("victordaniels@yahoo.com");

    }

    @Test
    void getUserByEmail() {
        Optional<User> user1 = userRepository.getUserByEmailAddress(user.getEmailAddress());
        assertTrue(user1.isPresent());
    }

    @Test
    void getUserByEmailAddressAndPassword() {
        Optional<User> user1 = userRepository.getUserByEmailAddressAndPassword(user.getEmailAddress(), user.getPassword());
        assertTrue(user1.isPresent());
    }
}