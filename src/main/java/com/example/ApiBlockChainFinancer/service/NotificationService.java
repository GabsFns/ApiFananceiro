package com.example.ApiBlockChainFinancer.service;

import com.example.ApiBlockChainFinancer.model.Notification;
import com.example.ApiBlockChainFinancer.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    public Notification saveNotification(Notification notification){
        return notificationRepository.save(notification);
    }

    public List<Notification> getNotificationsByUser(Long userId){
        return notificationRepository.findByUserId(userId);
    }

    public void deleteNotification(Long id){
        notificationRepository.deleteById(id);
    }
}
