package com.flightdata_handler.service.eventListeners;

import com.flightdata_handler.events.FlightModifiedEvent.FlightDelayedEvent;
import com.flightdata_handler.events.FlightModifiedEvent.FlightLandedEvent;
import com.flightdata_handler.events.FlightModifiedEvent.FlightTakenoffEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component

public class FlightEventListener {
    // feignClient flightDelayedController
    // feignClient flightLandedController
    // feignClient flightTakenoffController

    @EventListener
    public void onFlightDelayedEvent(FlightDelayedEvent event) {
        // Convert event to Notifications_Handler FlightDelayedEvent DTO)
        // Send notification via corresponding FeignClient
    }


    @EventListener
    public void onFlightLandedEvent(FlightLandedEvent event) {

    }

    @EventListener
    public void onFlightTakenoffEvent(FlightTakenoffEvent event) {

    }
}
