package com.example.demo.entity;

import com.example.demo.dto.ProductInfo;
import io.swagger.models.auth.In;
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
    @Column(unique = true)
    private String nameProduct;
    private int price;
    private String urlImageProd;
    private String description;
    private int orderId;
    private String listSize;

//
//    @OneToMany(
//            mappedBy = "product",
//            cascade = CascadeType.ALL,
//            orphanRemoval = true
//    )
//    private List<ProductSizeEntity>listSizeProd = new ArrayList<>();
//
//    public void addSize(ProductSizeEntity size) {
//        listSizeProd.add(size);
//        size.set(this);
//    }
//
//    public void removeSize(ProductSizeEntity size) {
//        listSizeProd.remove(size);
//        size.setProduct(null);
//    }
}
