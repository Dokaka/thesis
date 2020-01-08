package com.example.demo.service.impl;

import com.example.demo.dto.NotificationDto;
import com.example.demo.entity.NotificationEntity;
import com.example.demo.repository.INotificationRepository;
import com.example.demo.service.INotificationService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class NotificationService implements INotificationService {
    @Autowired
    private INotificationRepository notificationRepository;

    @Override
    public List<NotificationDto> getAllNotifications(){
        List<NotificationEntity> notificationEntityList = notificationRepository.findAll();
        List<NotificationDto> m_notificationDtoList = new CopyOnWriteArrayList<>();
        for (NotificationEntity notificationEntity : notificationEntityList){
            NotificationDto notificationDto = new NotificationDto();
            BeanUtils.copyProperties(notificationEntity,notificationDto);
            m_notificationDtoList.add(notificationDto);
        }
        return m_notificationDtoList;
    }
}
