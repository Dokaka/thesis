package com.example.demo.entity;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity(name = "products")

public class ProductEntity {
    @Id
    @GeneratedValue
    @Column(nullable = false, unique = true)
    private Long id;
    private String name_prod;
    private int price;
    private String url_Image_prod;
    private String description;
    private int order_id;
    private int size;
//    @OneToMany(
//            mappedBy = "productEntity",
//            cascade =  CascadeType.ALL,
//            orphanRemoval = true
//    )
//    private List<ProductSizeEntity> listProductSize;
//    public void addSize(ProductSizeEntity productSizeEntity){
//        listProductSize.add(productSizeEntity);
//        productSizeEntity.setProductEntity(this);
//    }
//    public void removeSize(ProductSizeEntity productSizeEntity){
//        listProductSize.remove(productSizeEntity);
//        productSizeEntity.setProductEntity(null);
//    }
}
