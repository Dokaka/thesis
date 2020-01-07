package com.example.demo.controller;

import com.example.demo.dto.ProductDto;
import com.example.demo.dto.UserDto;
import com.example.demo.service.IProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
@Api(value = "Products APIs")
public class ProductController {
    @Autowired
    private IProductService productService;

    @ApiOperation(value = "Get list of products", response = ProductDto.class)
    @ApiResponses({
            @ApiResponse(code = 400, message="Bad request"),
            @ApiResponse(code = 404, message="Not found"),
            @ApiResponse(code = 500, message="Internal Server Error"),
    })
    @GetMapping()
    public ResponseEntity<?> getAllProducts(){
        List<ProductDto> productDtoList = productService.getAllProducts();
        return ResponseEntity.ok(productDtoList);
    }
//    @ApiOperation(value = "create a product", response = ProductDto.class)
//    @ApiResponses({
//            @ApiResponse(code = 400, message="Bad request"),
//            @ApiResponse(code = 404, message="Not found"),
//            @ApiResponse(code = 500, message="Internal Server Error"),
//    })
//    @PostMapping()
//    public ResponseEntity<?>createProduct(){
//
//    }

}
