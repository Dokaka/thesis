package com.example.demo.service.impl;

import com.example.demo.dto.OrderDto;
import com.example.demo.entity.OrderEntity;
import com.example.demo.entity.ProductEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.repository.IOrderRepository;
import com.example.demo.repository.IProductRepository;
import com.example.demo.repository.IUserRepository;
import com.example.demo.request.OrderRequest;
import com.example.demo.response.OrderResponse;
import com.example.demo.service.IOrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class OrderService implements IOrderService {
    @Autowired
    IOrderRepository orderRepository;
    @Autowired
    IProductRepository productRepository;
    @Autowired
    IUserRepository userRepository;



    @Override
    public List<OrderDto> getListOrderOfUser(long userId){
        List<OrderEntity> orderEntityList = orderRepository.findAllByUserId(userId);
        List<OrderDto> orderDtoList = new CopyOnWriteArrayList<>();
        for (OrderEntity orderEntity:orderEntityList){
            OrderDto orderDto = new OrderDto();
            BeanUtils.copyProperties(orderEntity,orderDto);
            orderDtoList.add(orderDto);
        }
        return orderDtoList;
    }

    @Override
    public OrderResponse createOrder(OrderRequest orderRequest){
        ProductEntity productEntity = productRepository.findByNameProduct(orderRequest.getNameProduct());
        System.out.println("hahahaha: "+productEntity.getPrice());

        UserEntity userEntity = userRepository.findByFullname(orderRequest.getUsername());
        OrderEntity orderEntity = new OrderEntity();

        BeanUtils.copyProperties(orderRequest,orderEntity);
        orderEntity.setProduct(productEntity);
        orderEntity.setPrice(productEntity.getPrice());
        System.out.println("orderEntity.getProductId(): "+orderEntity.getProduct());
        orderEntity.setUser(userEntity);
        System.out.println("orderEntity.getUserId(): "+orderEntity.getUser());

        //System.out.println("orderEntity is: " + orderEntity);

        OrderResponse orderResponse = new OrderResponse();
        BeanUtils.copyProperties(orderRepository.save(orderEntity),orderResponse);
        return orderResponse;
    }
}
