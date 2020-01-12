package com.example.demo.dto;

import lombok.Data;

@Data
public class OrderDto {
    private Long id;
    private String nameProduct;
    private int price;
    private int size;
    private String orderAddress;

}
