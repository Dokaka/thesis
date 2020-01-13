package com.example.demo.repository;

import com.example.demo.entity.OrderEntity;
import com.example.demo.entity.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOrderRepository extends CrudRepository<OrderEntity,Long> {
    List<OrderEntity> findAllByUserId(long userId);
}
