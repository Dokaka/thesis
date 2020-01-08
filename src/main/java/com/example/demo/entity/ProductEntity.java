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

    @OneToMany(
            mappedBy = "product",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<ProductSizeEntity> listSize = new ArrayList<>();

    public void addSize(ProductSizeEntity size) {
        listSize.add(size);
        size.setProduct(this);
    }

    public void removeSize(ProductSizeEntity size) {
        listSize.remove(size);
        size.setProduct(null);
    }
}
