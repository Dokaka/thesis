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
    @Column(name = "name_product",unique = true)
    private String nameProduct;
    @Column(name = "price")
    private int price;
    @Column(name = "url_image_prod")
    private String urlImageProd;
    @Column(name = "description")
    private String description;
    @Column(name = "order_id")
    private int orderId;
//    @Column(name = "list_size")
//    private String listSize;

}
