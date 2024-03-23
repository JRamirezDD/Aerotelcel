/*
 *    Title: Source Code
 *    Author: Ramirez de Diego, Jorge
 *    Date: 2024
 *    Code version: 1.0
 *    Availability: https://github.com/JRamirezDD/Aerotelcel
 */

package com.notifications_handler.dto;

import lombok.Data;

@Data
public abstract class NotificationData {
    String recipient;
    String body;
}
