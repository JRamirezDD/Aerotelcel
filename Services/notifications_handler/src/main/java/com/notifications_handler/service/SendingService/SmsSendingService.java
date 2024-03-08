package com.notifications_handler.service.SendingService;

import com.notifications_handler.dto.SmsNotification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SmsSendingService implements SendingService<SmsNotification> {
    @Override
    public void sendNotification(SmsNotification notificationData) {

    }
}
