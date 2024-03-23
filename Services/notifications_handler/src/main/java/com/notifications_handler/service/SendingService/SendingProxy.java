/*
 *    Title: Source Code
 *    Author: Ramirez de Diego, Jorge
 *    Date: 2024
 *    Code version: 1.0
 *    Availability: https://github.com/JRamirezDD/Aerotelcel
 */


package com.notifications_handler.service.SendingService;

import com.notifications_handler.dto.EmailNotification;
import com.notifications_handler.dto.NotificationData;
import com.notifications_handler.dto.SmsNotification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SendingProxy implements SendingService<NotificationData> {
    private final EmailSendingService emailSendingService;
    private final SmsSendingService smsSendingService;

    @Override
    public void sendNotification(NotificationData notificationData) {
        Class<?> notificationType = notificationData.getClass();

        if (notificationType == EmailNotification.class) {
            emailSendingService.sendNotification((EmailNotification) notificationData);
        }
        else if (notificationType == SmsNotification.class) {
            smsSendingService.sendNotification((SmsNotification) notificationData);
        }
    }
}
