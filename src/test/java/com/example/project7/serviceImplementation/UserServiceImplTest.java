package com.example.project7.serviceImplementation;

import com.example.project7.dto.LogInDTO;
import com.example.project7.dto.ResponseDTO;
import com.example.project7.model.User;
import com.example.project7.repository.UserRepository;
import com.example.project7.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserServiceImplTest {
    @Mock
   private UserRepository userRepository;
   private UserServiceImpl userServiceImpl;
   private AutoCloseable autoCloseable;
   private User user;
   private LogInDTO logInDTO;
   @BeforeEach
    void setUp(){
       userServiceImpl = new UserServiceImpl(userRepository);
       autoCloseable = MockitoAnnotations.openMocks(this);

       user = new User();
       user.setFirstName("Victor");
       user.setLastName("Daniels");
       user.setGender("male");
       user.setPassword("12345");
       user.setEmailAddress("victordaniels@yahoo.com");

       ResponseDTO response = new ResponseDTO();
       logInDTO = new LogInDTO();
       logInDTO.setEmailAddress(user.getEmailAddress());
       logInDTO.setPassword(user.getPassword());


    }
    @AfterEach
    void tearDown() throws Exception {
       autoCloseable.close();
    }

    @Test
    void addUser() throws Exception {
        ResponseDTO response = new ResponseDTO();
      ResponseDTO expected = userServiceImpl.addUser(user);
      User savedUser = userRepository.save(user);
            response.setData(savedUser);
            response.setMessage("Registration successful");
            response.setStatus(true);
       ResponseDTO actual = response;
       assertEquals(expected,actual);
    }

    @Test
    void canLogInUserSuccessfully() {
        ResponseDTO response = new ResponseDTO();
        ResponseDTO expected = userServiceImpl.logInUser(logInDTO);
        Optional<User> userDb = userRepository.getUserByEmailAddressAndPassword
                (logInDTO.getEmailAddress(), logInDTO.getPassword());
        if (userDb.isPresent()) {
            response.setStatus(true);
            response.setData(userDb.get());
            response.setMessage("LogIn successful");
            ResponseDTO actual = response;
            assertEquals(expected,actual);
        }



//        response.setMessage("Invalid email or password");
//        return response;
    }
}