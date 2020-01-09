package com.example.demo.service.impl;

import com.example.demo.dto.ProductDto;

import com.example.demo.entity.ProductEntity;
//import com.example.demo.entity.ProductSizeEntity;
import com.example.demo.entity.ProductSizeEntity;
import com.example.demo.exception.InternalServerException;
import com.example.demo.repository.IProductRepository;
import com.example.demo.service.IProductService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class ProductService implements IProductService {
    @Autowired
    private IProductRepository productRepository;
    @Override
    public List<ProductDto> getAllProducts(){
        List<ProductEntity> productEntityList = productRepository.findAll();
        List<ProductDto> productDtoList =  new CopyOnWriteArrayList<>();
        for(ProductEntity productEntity : productEntityList){
            ProductDto productDto = new ProductDto();
            BeanUtils.copyProperties(productEntity,productDto);
            productDtoList.add(productDto);
        }
        return productDtoList;
    }
    @Override
    public ProductDto createProduct(ProductDto productDto){

        ProductEntity productEntity = new ProductEntity();
        BeanUtils.copyProperties(productDto,productEntity);

        ProductDto m_productDto = new ProductDto();
        BeanUtils.copyProperties(productRepository.save(productEntity),m_productDto);

        return m_productDto;
    }

    @Override
    public void deleteProduct(Long id){
        try {
            productRepository.deleteById(id);
        } catch (Exception ex){
            throw new InternalServerException("Error when delete product information");
        }
    }

    @Override
    public ProductDto getProductByName(String nameProduct){
        ProductEntity productEntity = productRepository.findByNameProduct(nameProduct);
        ProductDto productDto = new ProductDto();
        BeanUtils.copyProperties(productEntity,productDto);
        return productDto;
    }


}
