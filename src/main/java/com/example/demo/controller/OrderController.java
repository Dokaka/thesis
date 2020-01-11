package com.example.demo.controller;

import com.example.demo.dto.OrderDto;
import com.example.demo.entity.UserEntity;
import com.example.demo.repository.IOrderRepository;
import com.example.demo.request.OrderRequest;
import com.example.demo.response.OrderResponse;
import com.example.demo.service.IOrderService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/orders")
@Api(value = "Orders APIs")
public class OrderController {
    @Autowired
    private IOrderRepository orderRepository;
    @Autowired
    private IOrderService orderService;

    @PostMapping()
    public ResponseEntity<?> createOrder(@RequestBody @Valid OrderRequest orderRequest){
        OrderResponse orderResponse = orderService.createOrder(orderRequest);
        return ResponseEntity.ok(orderResponse);
    }
    @GetMapping("/{userId}")
    public ResponseEntity<?> getListOrderOfUser(@PathVariable long userId){
        List<OrderDto> orderDtoList = orderService.getListOrderOfUser(userId);
        return ResponseEntity.ok(orderDtoList);
    }
}
