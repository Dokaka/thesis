package com.example.demo.request;

import lombok.Data;

@Data
public class OrderRequest {
    private String nameProduct;
    private int size;
    private String phone;
}
