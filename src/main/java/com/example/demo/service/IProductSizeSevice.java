package com.example.demo.service;

import com.example.demo.dto.ProductInfo;

import java.util.List;

public interface IProductSizeSevice {
    public ProductInfo getProductInfo(int productId);
    public List<ProductInfo> getListProductInfo();
}
