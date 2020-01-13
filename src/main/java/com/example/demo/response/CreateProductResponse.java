package com.example.demo.response;

import lombok.Data;

@Data
public class CreateProductResponse {
    private String nameProduct;
    private int price;
    private String urlImageProd;
    private String description;
}
