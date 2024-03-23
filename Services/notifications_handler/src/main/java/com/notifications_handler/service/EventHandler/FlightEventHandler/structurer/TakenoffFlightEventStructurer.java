/*
 *    Title: Source Code
 *    Author: Ramirez de Diego, Jorge
 *    Date: 2024
 *    Code version: 1.0
 *    Availability: https://github.com/JRamirezDD/Aerotelcel
 */


package com.notifications_handler.service.EventHandler.FlightEventHandler.structurer;

import com.flightdata_handler.events.FlightModifiedEvent.FlightTakenoffEvent;
import com.notifications_handler.dto.EmailNotification;
import com.notifications_handler.dto.SmsNotification;
import org.springframework.stereotype.Component;

@Component
public class TakenoffFlightEventStructurer implements FlightEventStructurer<FlightTakenoffEvent> {
    @Override
    public EmailNotification convertToEmail(FlightTakenoffEvent event, String recipient) {
        return null;
    }

    @Override
    public SmsNotification convertToSms(FlightTakenoffEvent event, String recipient) {
        return null;
    }
}
