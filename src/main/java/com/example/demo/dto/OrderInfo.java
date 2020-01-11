//package com.example.demo.dto;
//
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import java.io.IOException;
//import java.util.List;
//
//@Setter
//@Getter
//@NoArgsConstructor
//
//public class OrderInfo {
//    private Long id;
//    private String nameProduct;
//    private int size;
//    private List<OrderDto> listOrder;
//    public OrderInfo(Long id, String nameProduct, Integer size, Object listOrder){
//        this.id = id;
//        this.nameProduct = nameProduct;
//        this.size = size;
//        if (listOrder != null){
//            ObjectMapper mapper = new ObjectMapper();
//            try {
//                this.listOrder = mapper.readValue((String) listOrder, new TypeReference<List<OrderDto>>(){});
//            } catch (IOException e) {
//                this.listOrder = null;
//            }
//        }
//    }
//}
