package com.example.demo.controller;

import com.example.demo.dto.BannerDto;
import com.example.demo.dto.NotificationDto;
import com.example.demo.service.IBannerService;
import com.example.demo.service.INotificationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/banners")
@Api(value = "Banners APIs")
public class BannerController {
    @Autowired
    private IBannerService bannerService;

    @ApiOperation(value = "Get list of banners", response = BannerDto.class)
    @ApiResponses({
            @ApiResponse(code = 400, message="Bad request"),
            @ApiResponse(code = 500, message="Internal Server Error"),
    })
    @GetMapping()
    public ResponseEntity<?> getAllBanners(){
        List<BannerDto> bannerDtoList = bannerService.getAllBanner();
        return ResponseEntity.ok(bannerDtoList);
    }
}
