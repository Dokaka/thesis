package com.example.demo.response;

import lombok.Data;

@Data
public class CreateUserResponse {
    private String fullname;
    private String phone;
    private String email;
    private String address;
    private String password;
}
