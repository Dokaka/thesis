package com.example.demo.service;

import com.example.demo.dto.ProductDto;


import java.util.List;

public interface IProductService {
    List<ProductDto> getAllProducts();
    ProductDto createProduct(ProductDto productDto);
}
