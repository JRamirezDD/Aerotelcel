package com.notifications_handler.service.FlightEventHandler.structurer;

import com.flightdata_handler.events.FlightModifiedEvent.FlightDelayedEvent;
import com.flightdata_handler.model.Flight;
import com.notifications_handler.dto.EmailNotification;
import com.notifications_handler.dto.SmsNotification;
import org.springframework.stereotype.Component;

@Component
public class DelayedFlightEventStructurer implements FlightEventStructurer<FlightDelayedEvent> {
    public EmailNotification convertToEmail(FlightDelayedEvent event) {
        Flight flight = event.getFlight(); // Assuming getAirport() should actually be getFlight()
        String htmlContent = "<h1>Flight Delay Notification</h1>" +
                "<p>Flight <b>" + flight.getCallsign() + "</b> to " + flight.getDestination() +
                " has been delayed.</p>" +
                "<p>Original ETA: " + event.getOldTime().toString() + "<br>" +
                "New ETA: " + event.getNewTime().toString() + "</p>";

        EmailNotification notification = new EmailNotification();
        notification.setDestination("recipient@example.com"); // Set the recipient address
        notification.setBody(htmlContent);
        return notification;
    }


    @Override
    public SmsNotification convertToSms(FlightDelayedEvent event) {
        return null;
    }
}
