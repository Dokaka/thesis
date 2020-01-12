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

//@SqlResultSetMappings(
//        value = {
//                @SqlResultSetMapping(
//                        name = "orderInfo",
//                        classes = @ConstructorResult(
//                                targetClass = OrderInfo.class,
//                                columns = {
//                                        @ColumnResult(name = "id_order", type = Long.class),
//                                        @ColumnResult(name = "name_product",type = String.class),
//                                        @ColumnResult(name = "size",type = Integer.class),
//                                        @ColumnResult(name = "list_order", type = String.class)
//                                }
//                        )
//                )
//        }
//)
//@NamedNativeQuery(
//        name = "getOrderInfo",
//        resultSetMapping = "orderInfo",
//        query = "SELECT prd.id as idPro ,prd.name_product as name,prd.price as pricePro," +
//                "prd.url_image_prod as image , prd.description as des,\n" +
//                "(\n" +
//                "\tSELECT JSON_ARRAYAGG(JSON_OBJECT('id',pz.id,'size',pz.size)) \n" +
//                "\tFROM product_size pz \n" +
//                "    WHERE pz.product_id = prd.id\n" +
//                ") as list_size \n" +
//                "FROM products prd \n" +
//                "WHERE prd.id = ?1"
//)

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
