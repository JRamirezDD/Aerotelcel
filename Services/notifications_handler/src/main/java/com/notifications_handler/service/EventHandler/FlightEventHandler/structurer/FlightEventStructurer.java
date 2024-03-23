/*
 *    Title: Source Code
 *    Author: Ramirez de Diego, Jorge
 *    Date: 2024
 *    Code version: 1.0
 *    Availability: https://github.com/JRamirezDD/Aerotelcel
 */


package com.notifications_handler.service.EventHandler.FlightEventHandler.structurer;

import com.flightdata_handler.events.FlightModifiedEvent.FlightModifiedEvent;
import com.notifications_handler.dto.EmailNotification;
import com.notifications_handler.dto.SmsNotification;

public interface FlightEventStructurer<E extends FlightModifiedEvent> {
    public EmailNotification convertToEmail(E event, String recipient);
    public SmsNotification convertToSms(E event, String recipient);
}
