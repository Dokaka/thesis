package com.example.demo.servicetest;

import com.example.demo.controller.ProductController;
import com.example.demo.dto.ProductDto;
import com.example.demo.entity.ProductEntity;
import com.example.demo.entity.ProductSizeEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.repository.IProductRepository;
import com.example.demo.repository.IProductSizeRepository;
import com.example.demo.request.UpdateProductRequest;
import com.example.demo.response.UpdateProductResponse;
import com.example.demo.service.impl.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class ProductServiceTest {

    private ProductDto productDto;
    private ProductEntity productEntity;
    private UpdateProductRequest updateProductRequest;
    private UpdateProductResponse updateProductResponse;
    private ProductSizeEntity productSizeEntity;


//    @InjectMocks
//    ProductController productController;

    @InjectMocks
    ProductService productService;

    @Mock
    IProductRepository productRepository;

    @Mock
    IProductSizeRepository productSizeRepository;

    @BeforeEach
    void initProduct(){
        MockitoAnnotations.initMocks(this);

        productDto = new ProductDto();
        productDto.setNameProduct("testNameProductDto");
        productDto.setPrice(500000);
        productDto.setUrlImageProd("testUrlProductDto");
        productDto.setDescription("testDescriptionDto");

        productEntity = new ProductEntity();
        productEntity.setNameProduct("testNameProductEntity");
        productEntity.setPrice(500000);
        productEntity.setUrlImageProd("testUrlProductEntity");
        productEntity.setDescription("testDescriptionEntity");

        updateProductRequest = new UpdateProductRequest();
        updateProductRequest.setNameProduct("testUpdateProductRequest");
        updateProductRequest.setPrice(500000);
        updateProductRequest.setUrlImageProd("testUpdateProductRequest1");
        updateProductRequest.setDescription("testUpdateProductRequest2");

        updateProductResponse = new UpdateProductResponse();
        updateProductResponse.setMessage("Update product completely");

        productSizeEntity = new ProductSizeEntity();
        productSizeEntity.setSize(productSizeEntity.getSize());

    }

    @Test
    void testGetListProduct() throws Exception{

        List<ProductEntity> productEntityList = new CopyOnWriteArrayList<>();
        productEntityList.add(productEntity);
        when(productRepository.findAll()).thenReturn(productEntityList);
        List<ProductDto> productDtoList = productService.getAllProducts();
        assertNotNull(productDtoList);
        assertEquals(productDtoList.get(0).getNameProduct(),productEntity.getNameProduct());
    }
    @Test
    void testGetProductByName()throws Exception{

        when(productRepository.findByNameProduct(anyString())).thenReturn(productEntity);
        ProductDto productDto1 = productService.getProductByName(anyString());
        assertNotNull(productDto1);
        assertEquals(productEntity.getNameProduct(),productDto1.getNameProduct());
    }

    @Test
    void testUpdateProduct()throws Exception{

        when(productRepository.findByNameProduct(anyString())).thenReturn(productEntity);
        when(productRepository.save(any(ProductEntity.class))).thenReturn(productEntity);
        UpdateProductResponse updateProductResponse = productService.updateProduct(updateProductRequest, productEntity.getNameProduct());

        assertNotNull(updateProductResponse);
        assertEquals(updateProductResponse.getMessage(),"Update product completely");

    }
    @Test
    void testCreateProduct() throws Exception{
        //when(productRepository.findByNameProduct(anyString())).thenReturn(null);
       // when(productRepository.findByEmail(anyString())).thenReturn(null);
        when(productRepository.save(any(ProductEntity.class))).thenReturn(productEntity);
        when(productSizeRepository.save(any(ProductSizeEntity.class))).thenReturn(productSizeEntity);
        ProductDto productDto1 = productService.createProduct(productDto);
        assertNotNull(productDto1);
        assertEquals(productEntity.getNameProduct(),productDto1.getNameProduct());
    }

}
