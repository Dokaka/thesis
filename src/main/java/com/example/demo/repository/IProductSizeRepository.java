package com.example.demo.repository;

import com.example.demo.dto.ProductInfo;
import com.example.demo.entity.ProductSizeEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IProductSizeRepository extends CrudRepository<ProductSizeEntity,Long> {
    @Query(nativeQuery = true, name = "getProductInfo")
    ProductInfo getProductInfo(int productId);
    @Query(nativeQuery = true, name = "getListProductInfo")
    List<ProductInfo> getListProductInfo();
}
