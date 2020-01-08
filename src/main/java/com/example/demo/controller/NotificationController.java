package com.example.demo.controller;

import com.example.demo.dto.NotificationDto;
import com.example.demo.repository.INotificationRepository;
import com.example.demo.request.CreateBannerRequest;
import com.example.demo.service.INotificationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/notifications")
@Api(value = "Notifications APIs")
public class NotificationController {
    @Autowired
    private INotificationService notificationService;

    @ApiOperation(value = "Get list of notifications", response = NotificationDto.class)
    @ApiResponses({
            @ApiResponse(code = 400, message="Bad request"),
            @ApiResponse(code = 500, message="Internal Server Error"),
    })
    @GetMapping()
    public ResponseEntity<?> getAllNotifications(){
        List<NotificationDto> notificationDtoList = notificationService.getAllNotifications();
        return ResponseEntity.ok(notificationDtoList);
    }




}
