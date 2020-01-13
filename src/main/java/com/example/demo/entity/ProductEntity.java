package com.example.demo.entity;

import lombok.Data;
import javax.persistence.*;
import java.util.List;

@Data
@Entity(name = "products")

public class ProductEntity {
    @Id
    @GeneratedValue
    @Column(name = "id",nullable = false, unique = true)
    private Long id;

    @Column(name = "name_product",unique = true)
    private String nameProduct;

    @Column(name = "price")
    private int price;

    @Column(name = "url_image_prod", length = 600)
    private String urlImageProd;

    @Column(name = "description",length = 1000)
    private String description;

    @OneToMany(mappedBy = "productId",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<ProductSizeEntity> listSize;

}
