package com.example.demo.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
@Data
public class LoginUserRequest {
    @NotNull(message = "phone is required")
    @NotEmpty(message = "phone is required")
    @ApiModelProperty(
            example="12345678",
            notes="Phone cannot be empty",
            required=true
    )
    private String phone;
    @NotNull(message = "password is required")
    @NotEmpty(message = "password is required")
    @ApiModelProperty(
            example="1@3$5asd",
            notes="password cannot be empty",
            required=true
    )
    private String password;


}
