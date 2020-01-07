package com.example.demo.repository;

import com.example.demo.entity.ProductEntity;
import com.example.demo.entity.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductRepository extends CrudRepository<ProductEntity,Long> {
    List<ProductEntity> findAll();
}
