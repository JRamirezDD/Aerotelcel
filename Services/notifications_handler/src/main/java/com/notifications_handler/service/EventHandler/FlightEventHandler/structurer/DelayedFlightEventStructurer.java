/*
 *    Title: Source Code
 *    Author: Ramirez de Diego, Jorge
 *    Date: 2024
 *    Code version: 1.0
 *    Availability: https://github.com/JRamirezDD/Aerotelcel
 */


package com.notifications_handler.service.EventHandler.FlightEventHandler.structurer;

import com.flightdata_handler.events.FlightModifiedEvent.FlightDelayedEvent;
import com.flightdata_handler.model.Flight;
import com.notifications_handler.dto.EmailNotification;
import com.notifications_handler.dto.SmsNotification;
import org.springframework.stereotype.Component;

@Component
public class DelayedFlightEventStructurer implements FlightEventStructurer<FlightDelayedEvent> {
    public EmailNotification convertToEmail(FlightDelayedEvent event, String recipient) {
        Flight flight = event.getFlight(); // Assuming getAirport() should actually be getFlight()
        String htmlContent = "<h1>Flight Delay Notification</h1>" +
                "<p>Flight <b>" + flight.getCallsign() +
                " has been delayed.</p>" +
                "<p>Original ETA: " + event.getOldTime().toString() + "<br>" +
                "New ETA: " + event.getNewTime().toString() + "</p>";

        EmailNotification notification = new EmailNotification();
        notification.setRecipient(recipient); // Set the recipient address
        notification.setBody(htmlContent);
        return notification;
    }


    @Override
    public SmsNotification convertToSms(FlightDelayedEvent event, String recipient) {
        return null;
    }
}
