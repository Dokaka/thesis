package com.example.demo.response;

import com.example.demo.dto.TokenResponse;
import lombok.Data;

@Data
public class LoginUserResponse {

    private TokenResponse tokenResponse;
    private String fullname;
    private String phone;
    private String email;
    private String address;
}
