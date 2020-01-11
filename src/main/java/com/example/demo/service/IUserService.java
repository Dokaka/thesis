package com.example.demo.service;

import com.example.demo.dto.TokenResponse;
import com.example.demo.dto.UserDto;
import com.example.demo.request.CreateUserRequest;
import com.example.demo.request.LoginUserRequest;

import java.util.List;

public interface IUserService {
    List<UserDto> getAllUsers();
    UserDto getUserByPhone(String phone);
    UserDto createUser(CreateUserRequest createUserRequest);
    void deleteUser(Long id);
    UserDto updateUser(CreateUserRequest createUserRequest, String fullname);
    TokenResponse login(LoginUserRequest loginUserRequest);
    boolean checkUser(CreateUserRequest createUserRequest);
    UserDto registerUser(CreateUserRequest createUserRequest);
}
