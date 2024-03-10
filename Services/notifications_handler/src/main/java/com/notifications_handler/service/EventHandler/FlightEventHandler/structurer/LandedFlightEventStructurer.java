package com.notifications_handler.service.EventHandler.FlightEventHandler.structurer;

import com.flightdata_handler.events.FlightModifiedEvent.FlightLandedEvent;
import com.notifications_handler.dto.EmailNotification;
import com.notifications_handler.dto.SmsNotification;
import org.springframework.stereotype.Component;

@Component
public class LandedFlightEventStructurer implements FlightEventStructurer<FlightLandedEvent> {
    @Override
    public EmailNotification convertToEmail(FlightLandedEvent event, String recipient) {
        return null;
    }

    @Override
    public SmsNotification convertToSms(FlightLandedEvent event, String recipient) {
        return null;
    }
}
