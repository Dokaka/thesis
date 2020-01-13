package com.example.demo.service.impl;

import com.example.demo.dto.OrderDto;
import com.example.demo.dto.UserDto;
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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    public List<OrderDto> getListOrderOfUser(long userId) {
        List<OrderEntity> orderEntityList = orderRepository.findAllByUserId(userId);
        List<OrderDto> orderDtoList = new CopyOnWriteArrayList<>();
        for (OrderEntity orderEntity : orderEntityList) {
            OrderDto orderDto = new OrderDto();
            BeanUtils.copyProperties(orderEntity, orderDto);
            orderDtoList.add(orderDto);
        }
        return orderDtoList;
    }

    @Override
    public OrderResponse createOrder(OrderRequest orderRequest) {
        try {
            OrderResponse orderResponse = new OrderResponse();
            ProductEntity productEntity = productRepository.findByNameProduct(orderRequest.getNameProduct());
            UserEntity userEntity = userRepository.findByPhone(orderRequest.getPhone());
            if (productEntity == null) {
                orderResponse.setMessage("Name of product is wrong");
                orderResponse.setStatus(false);
                return orderResponse;
            } else if (userEntity == null) {
                orderResponse.setMessage("Phone of user is wrong");
                orderResponse.setStatus(false);
                return orderResponse;
            } else if (orderRequest.getSize() > 45 || orderRequest.getSize() < 40) {
                orderResponse.setMessage("Don't have this size");
                orderResponse.setStatus(false);
                return orderResponse;
            } else {
                OrderEntity orderEntity = new OrderEntity();
                BeanUtils.copyProperties(orderRequest, orderEntity);
                orderEntity.setProduct(productEntity);
                orderEntity.setPrice(productEntity.getPrice());
                System.out.println("orderEntity.getProductId(): " + orderEntity.getProduct());
                orderEntity.setUser(userEntity);
                System.out.println("orderEntity.getUserId(): " + orderEntity.getUser());
                //System.out.println("orderEntity is: " + orderEntity);
                orderRepository.save(orderEntity);
                //BeanUtils.copyProperties(orderRepository.save(orderEntity),orderResponse);
                orderResponse.setMessage("Create order completely");
                orderResponse.setStatus(true);
                return orderResponse;
            }
        } catch (Exception e) {
            OrderResponse orderResponse = new OrderResponse();
            orderResponse.setMessage("Create order fail");
            orderResponse.setStatus(false);
            return orderResponse;
        }
    }

    @Override
    public List<OrderDto> getListOrder() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String phone = (String) auth.getPrincipal();
        System.out.println("phone of user is : " + phone);
        UserEntity userEntity = userRepository.findByPhone(phone);
        Long userId = userEntity.getId();
        List<OrderEntity> orderEntityList = orderRepository.findAllByUserId(userId);
        List<OrderDto> orderDtoList = new CopyOnWriteArrayList<>();
        for (OrderEntity orderEntity : orderEntityList) {
            OrderDto orderDto = new OrderDto();
            BeanUtils.copyProperties(orderEntity, orderDto);
            orderDtoList.add(orderDto);
        }
        return orderDtoList;
    }
}
