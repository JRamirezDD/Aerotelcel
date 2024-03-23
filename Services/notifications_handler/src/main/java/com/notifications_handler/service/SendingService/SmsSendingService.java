/*
 *    Title: Source Code
 *    Author: Ramirez de Diego, Jorge
 *    Date: 2024
 *    Code version: 1.0
 *    Availability: https://github.com/JRamirezDD/Aerotelcel
 */


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
