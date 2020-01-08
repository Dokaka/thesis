package com.example.demo.request;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateProductRequest {
    private String name_prod;
    private int price;
    private String url_Image_prod;
    private String description;
    private int order_id;
}