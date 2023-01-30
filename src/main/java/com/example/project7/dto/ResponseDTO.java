package com.example.project7.dto;

import com.example.project7.model.User;
import lombok.Data;

import java.util.List;


@Data
public class ResponseDTO {
    private String message;
    private User data;
    private boolean status;

    private List<User> userList;


}
