package com.example.demo.response;

import lombok.Data;

@Data
public class GetUserResponse {
    private String fullname;
    private String phone;
    private String email;
    private String address;
}
