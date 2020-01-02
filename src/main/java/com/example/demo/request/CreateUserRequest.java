package com.example.demo.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
@Data
public class CreateUserRequest {
    @NotNull(message = "fullname is required")
    @NotEmpty(message = "fullname is required")
    @ApiModelProperty(
            example="anderadored",
            notes="Fullname cannot be empty",
            required=true
    )
    private String fullname;

    @NotNull(message = "phone is required")
    @NotEmpty(message = "phone is required")
    @ApiModelProperty(
            example="12345678",
            notes="Phone cannot be empty",
            required=true
    )
    private String phone;

    @NotNull(message = "email is required")
    @NotEmpty(message = "email is required")
    @ApiModelProperty(
            example="ander@123",
            notes="email cannot be empty",
            required=true
    )
    private String email;

    @NotNull(message = "address is required")
    @NotEmpty(message = "address is required")
    @ApiModelProperty(
            example="hanoi",
            notes="address cannot be empty",
            required=true
    )
    private String address;

    @NotNull(message = "password is required")
    @NotEmpty(message = "password is required")
    @ApiModelProperty(
            example="1@3$5asd",
            notes="password cannot be empty",
            required=true
    )
    private String password;
}
