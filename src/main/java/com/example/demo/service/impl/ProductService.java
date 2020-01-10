package com.example.demo.service.impl;

import com.example.demo.dto.ProductDto;

import com.example.demo.entity.ProductEntity;
//import com.example.demo.entity.ProductSizeEntity;
import com.example.demo.entity.ProductSizeEntity;
import com.example.demo.exception.InternalServerException;
import com.example.demo.repository.IProductRepository;
import com.example.demo.repository.IProductSizeRepository;
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
    @Autowired
    private IProductSizeRepository productSizeRepository;
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
        // luu data vao bang size
        //ProductEntity productEntity1 = productRepository.findByNameProduct(productEntity.getNameProduct());

        ProductSizeEntity productSizeEntity1 = new ProductSizeEntity();
        productSizeEntity1.setSize(42);
        productSizeEntity1.setProductId(productEntity);
        ProductSizeEntity productSizeEntity2 = new ProductSizeEntity();
        productSizeEntity2.setSize(43);
        productSizeEntity2.setProductId(productEntity);

        productSizeRepository.save(productSizeEntity1);
        productSizeRepository.save(productSizeEntity2);

        //

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
