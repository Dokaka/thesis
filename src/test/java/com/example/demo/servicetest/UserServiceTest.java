package com.example.demo.servicetest;

import com.example.demo.response.GetUserResponse;
import com.example.demo.service.impl.UserService;
import org.mockito.InjectMocks;

public class UserServiceTest {

    private GetUserResponse userResponse;

    @InjectMocks
    UserService userService;

}
