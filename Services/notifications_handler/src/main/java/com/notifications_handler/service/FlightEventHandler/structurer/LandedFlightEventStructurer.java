package com.notifications_handler.service.FlightEventHandler.structurer;

import com.flightdata_handler.events.FlightModifiedEvent.FlightLandedEvent;
import com.notifications_handler.dto.EmailNotification;
import com.notifications_handler.dto.SmsNotification;
import org.springframework.stereotype.Component;

@Component
public class LandedFlightEventStructurer implements FlightEventStructurer<FlightLandedEvent> {
    @Override
    public EmailNotification convertToEmail(FlightLandedEvent event) {
        return null;
    }

    @Override
    public SmsNotification convertToSms(FlightLandedEvent event) {
        return null;
    }
}
