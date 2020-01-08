package com.example.demo.service;

import com.example.demo.dto.NotificationDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface INotificationService {
    List<NotificationDto> getAllNotifications();
    //NotificationDto createNotification(NotificationDto notificationDto);
}
