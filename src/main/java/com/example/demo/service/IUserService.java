package com.example.demo.service;

import com.example.demo.dto.UserDto;
import com.example.demo.request.CreateUserRequest;

import java.util.List;

public interface IUserService {
    List<UserDto> getAllUsers();
    UserDto getUserByPhone(String phone);
    UserDto createUser(UserDto userDto);
    void deleteUser(Long id);
    UserDto updateUser(CreateUserRequest createUserRequest, String fullname);
    UserDto login(String phone, String password);
    boolean checkUser(CreateUserRequest createUserRequest);
    UserDto registerUser(CreateUserRequest createUserRequest);
}
