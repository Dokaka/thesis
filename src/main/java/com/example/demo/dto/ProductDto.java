package com.example.demo.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProductDto {
    private Long id;
    private String nameProduct;
    private int price;
    private String urlImageProd;
    private String description;
    private int orderId;
}
