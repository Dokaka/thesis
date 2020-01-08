package com.example.demo.dto;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Setter
@Getter
@Component

public class ProductInfo {
    private long id;
    private String nameProduct;
    private int price;
    private String urlImageProd;
    private String description;
    private int orderId;
    private List<ProductSizeDto> listSize;

    public ProductInfo() {

    }

    public ProductInfo(long id,String nameProduct,int price,String urlImageProd,String description,int orderId,String listSize) {
        this.id = id;
        this.nameProduct = nameProduct;
        this.price = price;
        this.urlImageProd = urlImageProd;
        this.description = description;
        this.orderId = orderId;
        if (listSize != null) {
            ObjectMapper mapper = new ObjectMapper();
            try {
                this.listSize = mapper.readValue((String) listSize, new TypeReference<List<ProductSizeDto>>(){});
            } catch (IOException e) {
                this.listSize = null;
            }
        }
    }
}
