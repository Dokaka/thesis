package com.example.demo.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderDto {
    private Long id;
    private String nameProduct;
    private int price;
    private int size;
    private String orderAddress;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
