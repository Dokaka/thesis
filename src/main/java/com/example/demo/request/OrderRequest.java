package com.example.demo.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class OrderRequest {

    @NotNull(message = "name of product is required")
    @NotEmpty(message = "name of product is required")
    @ApiModelProperty(
            example="adidas555",
            notes="name of product cannot be empty",
            required=true
    )
    private String nameProduct;

    @NotNull(message = "size of product is required")
    @ApiModelProperty(
            example="42",
            notes="user choose the size",
            required=true
    )
    private int size;

    @NotNull(message = "phone is required")
    @NotEmpty(message = "phone is required")
    @ApiModelProperty(
            example="12345678",
            notes="phone of user",
            required=true
    )
    private String phone;
    @NotNull(message = "orderAddress is required")
    @NotEmpty(message = "orderAddress is required")
    @ApiModelProperty(
            example="Hoa lac, Ha noi",
            notes="orderAddress of user",
            required=true
    )
    private String orderAddress;
}
