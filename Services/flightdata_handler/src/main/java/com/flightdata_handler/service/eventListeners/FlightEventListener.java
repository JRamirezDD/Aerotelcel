package com.flightdata_handler.service.eventListeners;

import com.flightdata_handler.events.FlightModifiedEvent.FlightDelayedEvent;
import com.flightdata_handler.events.FlightModifiedEvent.FlightLandedEvent;
import com.flightdata_handler.events.FlightModifiedEvent.FlightTakenoffEvent;
import com.flightdata_handler.service.FeignClients.Client_NotificationsHandler_FlightEventReceiver;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FlightEventListener {
    final Client_NotificationsHandler_FlightEventReceiver flightEventReceiverClient;

    @EventListener
    public void onFlightDelayedEvent(FlightDelayedEvent event) {
        // Convert event to Notifications_Handler FlightDelayedEvent DTO)
        // Send notification via corresponding FeignClient

        flightEventReceiverClient.delayedEvent(event);
    }


    @EventListener
    public void onFlightLandedEvent(FlightLandedEvent event) {
        flightEventReceiverClient.landedEvent(event);
    }

    @EventListener
    public void onFlightTakenoffEvent(FlightTakenoffEvent event) {
        flightEventReceiverClient.takenoffEvent(event);
    }
}
