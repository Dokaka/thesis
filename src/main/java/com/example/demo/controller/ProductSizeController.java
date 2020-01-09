package com.example.demo.controller;

import com.example.demo.dto.ProductInfo;
import com.example.demo.service.impl.ProductSizeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/productSize")
@Api(value = "ProductSize APIs")
public class ProductSizeController {
    @Autowired
    private ProductSizeService productSizeService;

    @ApiOperation(value="Get productInfo info by productId", response = ProductInfo.class)
    @GetMapping("/{id}")
    public ResponseEntity<?> getProductInfoById(@PathVariable int id) {
        ProductInfo productInfo = productSizeService.getProductInfo(id);
        return ResponseEntity.ok(productInfo);
    }

    @GetMapping()
    public ResponseEntity<?> getListProductInfo(){
        List<ProductInfo> productInfoList = productSizeService.getListProductInfo();
        return ResponseEntity.ok(productInfoList);
    }
}
