package com.example.demo.entity;

//import com.example.demo.dto.OrderInfo;
import com.example.demo.dto.ProductInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "orders")
public class OrderEntity extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    private String nameProduct;

    private int size;

    private int price;

    private String orderAddress;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne()
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "product_id")
    private ProductEntity product;
}
