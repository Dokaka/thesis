package com.example.demo.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity (name = "users")
public class UserEntity {
    @Id
    @GeneratedValue
    @Column(nullable = false, unique = true)
    private Long id;
    private String fullname;
    @Column(nullable = false, unique = true)
    private String phone;
    @Column(nullable = false, unique = true)
    private String email;
    private String address;
    private String password;
}
