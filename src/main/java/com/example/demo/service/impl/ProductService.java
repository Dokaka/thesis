package com.example.demo.service.impl;

import com.example.demo.dto.ProductDto;

import com.example.demo.entity.ProductEntity;
//import com.example.demo.entity.ProductSizeEntity;
import com.example.demo.entity.ProductSizeEntity;
import com.example.demo.exception.InternalServerException;
import com.example.demo.repository.IProductRepository;
import com.example.demo.repository.IProductSizeRepository;
import com.example.demo.request.UpdateProductRequest;
import com.example.demo.response.UpdateProductResponse;
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
        // save data to size table

        for(int i = 1; i <= 5; i++){
            ProductSizeEntity productSizeEntity = new ProductSizeEntity();
            productSizeEntity.setSize(39+i);
            productSizeEntity.setProductId(productEntity);
            productSizeRepository.save(productSizeEntity);
        }
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
        try{
            ProductEntity productEntity = productRepository.findByNameProduct(nameProduct);
            System.out.println("productDto: "+ productEntity);
            ProductDto productDto = new ProductDto();
            BeanUtils.copyProperties(productEntity,productDto);
            System.out.println("productDto: "+ productDto);
            return productDto;
        }
        catch (Exception e){
            return null;
        }

    }

    @Override
    public UpdateProductResponse updateProduct(UpdateProductRequest request, String nameProduct){
        ProductEntity productEntity = productRepository.findByNameProduct(nameProduct);
        if(productEntity == null ){
            UpdateProductResponse updateProductResponse = new UpdateProductResponse();
            updateProductResponse.setMessage("Update product failed");
            return updateProductResponse;
        }
        else {
            BeanUtils.copyProperties(request,productEntity);
            productRepository.save(productEntity);
            UpdateProductResponse updateProductResponse = new UpdateProductResponse();
            updateProductResponse.setMessage("Update product completely");
            return updateProductResponse;
        }
    }
}
