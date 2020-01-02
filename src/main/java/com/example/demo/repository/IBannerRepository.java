package com.example.demo.repository;

import com.example.demo.entity.BannerEntity;
import com.example.demo.entity.NotificationEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBannerRepository extends CrudRepository<BannerEntity,Long> {
    List<BannerEntity> findAll();
}
