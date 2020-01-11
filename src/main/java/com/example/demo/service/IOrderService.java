package com.example.demo.service;

import com.example.demo.dto.OrderDto;
import com.example.demo.entity.UserEntity;
import com.example.demo.request.OrderRequest;
import com.example.demo.response.OrderResponse;

import java.util.List;

public interface IOrderService {
    List<OrderDto> getListOrderOfUser(long userId);
    OrderResponse createOrder(OrderRequest orderRequest);
}
