package com.notifications_handler.controller.FlightEventReceiver;

import com.flightdata_handler.events.FlightModifiedEvent.FlightDelayedEvent;
import com.flightdata_handler.events.FlightModifiedEvent.FlightLandedEvent;
import com.flightdata_handler.events.FlightModifiedEvent.FlightTakenoffEvent;
import com.notifications_handler.service.EventHandler.FlightEventHandler.FlightEventHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequiredArgsConstructor
@Slf4j
public class FlightEventReceiver implements API_FlightEventReceiver {
    FlightEventHandler flightEventHandler;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public String home() { return "Notifications Handler -> FlightEventReceiver Home"; }

    public void delayedEvent(@RequestBody FlightDelayedEvent subscriptionRequest) {
        try {
            flightEventHandler.processEvent(subscriptionRequest);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    public void landedEvent(@RequestBody FlightLandedEvent subscriptionRequest) {
        try {
        flightEventHandler.processEvent(subscriptionRequest);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    public void takenoffEvent(@RequestBody FlightTakenoffEvent subscriptionRequest) {
        try {
            flightEventHandler.processEvent(subscriptionRequest);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
