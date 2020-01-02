package com.example.demo.dto;

import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String fullname;
    private String phone;
    private String email;
    private String address;
    private String password;

}
