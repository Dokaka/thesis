package com.example.demo.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class UpdateProductRequest {
    @NotNull(message = "nameProduct is required")
    @NotEmpty(message = "nameProduct is required")
    @ApiModelProperty(
            example="adidas555",
            notes="nameProduct cannot be empty",
            required=true
    )
    private String nameProduct;

    @NotNull(message = "price is required")
    @ApiModelProperty(
            example="500000",
            notes="price cannot be empty",
            required=true
    )
    private int price;

    @NotNull(message = "urlImageProd is required")
    @NotEmpty(message = "urlImageProd is required")
    @ApiModelProperty(
            example="http://testjfg.com.vn/",
            notes="urlImageProd cannot be empty",
            required=true
    )
    private String urlImageProd;

    @NotNull(message = "description is required")
    @NotEmpty(message = "description is required")
    @ApiModelProperty(
            example="best-seller in this year",
            notes="description cannot be empty",
            required=true
    )
    private String description;
}
