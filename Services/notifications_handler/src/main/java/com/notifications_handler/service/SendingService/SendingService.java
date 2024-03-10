package com.notifications_handler.service.SendingService;

import com.notifications_handler.dto.NotificationData;

@FunctionalInterface
public interface SendingService<N extends NotificationData> {
    void sendNotification(N notificationData);
}
