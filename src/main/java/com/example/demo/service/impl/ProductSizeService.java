package com.example.demo.service.impl;



import com.example.demo.dto.ProductInfo;
import com.example.demo.repository.IProductSizeRepository;
import com.example.demo.service.IProductSizeSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductSizeService implements IProductSizeSevice {
    @Autowired
    IProductSizeRepository productSizeRepository;

    @Override
    public ProductInfo getProductInfo(int productId){
        return productSizeRepository.getProductInfo(productId);
    }
    @Override
    public List<ProductInfo> getListProductInfo(){
        return productSizeRepository.getListProductInfo();
    }


}
