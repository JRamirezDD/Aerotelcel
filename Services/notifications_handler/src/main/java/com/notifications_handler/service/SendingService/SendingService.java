/*
 *    Title: Source Code
 *    Author: Ramirez de Diego, Jorge
 *    Date: 2024
 *    Code version: 1.0
 *    Availability: https://github.com/JRamirezDD/Aerotelcel
 */


package com.notifications_handler.service.SendingService;

import com.notifications_handler.dto.NotificationData;

@FunctionalInterface
public interface SendingService<N extends NotificationData> {
    void sendNotification(N notificationData);
}
