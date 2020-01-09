package com.example.demo.dto;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor

public class ProductInfo {
    private Long id;
    private String nameProduct;
    private int price;
    private String urlImageProd;
    private String description;
//    private int orderId;
    private List<ProductSizeDto> listSize;

    public ProductInfo(Long id,String nameProduct,Integer price,String urlImageProd,String description,Object listSize) {
        this.id = id;
        //System.out.println("value of id is"+id);
        this.nameProduct = nameProduct;
        this.price = price;
        this.urlImageProd = urlImageProd;
        this.description = description;
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
