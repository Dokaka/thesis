package com.example.demo.request;

import lombok.Data;

@Data
public class OrderRequest {
    private long id;
    private String nameProduct;
    private int size;
    private String username;
}
