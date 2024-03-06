package com.notifications_handler.service.FlightEventHandler.structurer;

import com.flightdata_handler.events.FlightModifiedEvent.FlightTakenoffEvent;
import com.notifications_handler.dto.EmailNotification;
import com.notifications_handler.dto.SmsNotification;
import org.springframework.stereotype.Component;

@Component
public class TakenoffFlightEventStructurer implements FlightEventStructurer<FlightTakenoffEvent> {
    @Override
    public EmailNotification convertToEmail(FlightTakenoffEvent event) {
        return null;
    }

    @Override
    public SmsNotification convertToSms(FlightTakenoffEvent event) {
        return null;
    }
}
