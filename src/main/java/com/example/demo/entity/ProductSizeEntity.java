package com.example.demo.entity;

import com.example.demo.dto.ProductInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@SqlResultSetMappings(
        value = {
                @SqlResultSetMapping(
                        name = "productInfo",
                        classes = @ConstructorResult(
                                targetClass = ProductInfo.class,
                                columns = {
                                        @ColumnResult(name = "idPro", type = Long.class),
                                        @ColumnResult(name = "name",type = String.class),
                                        @ColumnResult(name = "pricePro",type = Integer.class),
                                        @ColumnResult(name = "image",type = String.class),
                                        @ColumnResult(name = "des",type = String.class),
                                        @ColumnResult(name = "list_size", type = String.class)
                                }
                        )
                )
        }
)
@NamedNativeQuery(
        name = "getProductInfo",
        resultSetMapping = "productInfo",
        query = "SELECT prd.id as idPro ,prd.name_product as name,prd.price as pricePro," +
                "prd.url_image_prod as image , prd.description as des,\n" +
                "(\n" +
                "\tSELECT JSON_ARRAYAGG(JSON_OBJECT('id',pz.id,'size',pz.size)) \n" +
                "\tFROM product_size pz \n" +
                "    WHERE pz.product_id = prd.id\n" +
                ") as list_size \n" +
                "FROM products prd \n" +
                "WHERE prd.id = ?1"
)
@NamedNativeQuery(
        name = "getListProductInfo",
        resultSetMapping = "productInfo",
        query = "SELECT prd.id as idPro ,prd.name_product as name,prd.price as pricePro,prd.url_image_prod as image," +
                "prd.description as des, \n" +
                "(\n" +
                "\tSELECT JSON_ARRAYAGG(JSON_OBJECT('id',pz.id,'size',pz.size)) \n" +
                "\tFROM product_size pz \n" +
                "    WHERE pz.product_id = prd.id\n" +
                ") as list_size \n" +
                "FROM products prd"

)

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "product_size")

public class ProductSizeEntity {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;
    private  int size;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity productId;

}
