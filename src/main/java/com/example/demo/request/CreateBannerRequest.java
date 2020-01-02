package com.example.demo.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class CreateBannerRequest {
    @NotNull(message = "Title is required")
    @NotEmpty(message = "Title is required")
    @ApiModelProperty(
            example="Doanh thu hang ngay",
            notes="Title cannot be empty",
            required=true
    )
    private String title;

    @NotNull(message = "Content is required")
    @NotEmpty(message = "Content is required")
    @ApiModelProperty(
            example="Hom nay ban duoc 20 doi giay",
            notes="Content cannot be empty",
            required=true
    )
    private String content;
}
