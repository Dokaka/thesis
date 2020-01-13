package com.example.demo.repository;

import com.example.demo.entity.ProductEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductRepository extends CrudRepository<ProductEntity,Long> {
    List<ProductEntity> findAll();
    ProductEntity findByNameProduct(String nameProduct);
}
