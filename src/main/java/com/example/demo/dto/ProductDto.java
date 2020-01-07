package com.example.demo.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProductDto {
    private Long id;
    private String name_prod;
    private int price;
    private String url_Image_prod;
    private String description;
    private int order_id;
    private int product_size;
    //private List<Integer> listSize;
}
