package com.notifications_handler.dto;

import lombok.Data;

@Data
public abstract class NotificationData {
    String recipient;
    String body;
}