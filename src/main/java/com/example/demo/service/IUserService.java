package com.example.demo.service;

import com.example.demo.dto.UserDto;

import java.util.List;

public interface IUserService {
    List<UserDto> getAllUsers();
    UserDto getUserByPhone(String phone);
    UserDto createUser(UserDto userDto);

}
