package com.example.project7.service;

import com.example.project7.dto.LogInDTO;
import com.example.project7.dto.ResponseDTO;
import com.example.project7.model.User;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
public interface UserService {
    ResponseDTO addUser(User user) throws Exception;

    ResponseDTO logInUser(LogInDTO logInDTO);

//    ResponseDTO follow(Long userId);
//    ResponseDTO unfollow(Long userId);
//
//    List<User> getAllUser();

}
