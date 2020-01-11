package com.example.demo.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "notification")

@Data
public class NotificationEntity {
    @Id
    @GeneratedValue
    @Column(nullable = false, unique = true)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false, length = 500)
    private String content;

}
