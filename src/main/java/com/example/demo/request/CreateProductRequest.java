package com.example.demo.request;


import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CreateProductRequest {

    @NotNull(message = "nameProduct is required")
    @NotEmpty(message = "nameProduct is required")
    @ApiModelProperty(
            example="adidas555",
            notes="nameProduct cannot be empty",
            required=true
    )
    private String nameProduct;

    private int price;

    private String urlImageProd;

    private String description;

//    private int orderId;

}