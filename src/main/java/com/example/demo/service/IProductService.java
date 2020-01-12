package com.example.demo.service;

import com.example.demo.dto.ProductDto;
import com.example.demo.entity.ProductSizeEntity;
import com.example.demo.request.CreateProductRequest;
import com.example.demo.request.UpdateProductRequest;
import com.example.demo.response.UpdateProductResponse;


import java.util.List;

public interface IProductService {

    List<ProductDto> getAllProducts();
    ProductDto createProduct(ProductDto productDto);
    void deleteProduct(Long id);
    ProductDto getProductByName(String nameProduct);
    UpdateProductResponse updateProduct(UpdateProductRequest request, String nameProduct);

}
