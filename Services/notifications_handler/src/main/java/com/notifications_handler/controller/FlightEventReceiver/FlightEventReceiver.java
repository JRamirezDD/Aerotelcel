package com.notifications_handler.controller.FlightEventReceiver;

import com.flightdata_handler.events.FlightModifiedEvent.FlightDelayedEvent;
import com.flightdata_handler.events.FlightModifiedEvent.FlightLandedEvent;
import com.flightdata_handler.events.FlightModifiedEvent.FlightTakenoffEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notifications-handler/flight_event")
@RequiredArgsConstructor
@Slf4j
public class FlightEventReceiver implements API_FlightEventReceiver {

    public void delayedEvent(@RequestBody FlightDelayedEvent subscriptionRequest) {

    }

    public void landedEvent(@RequestBody FlightLandedEvent subscriptionRequest) {

    }

    public void takenoffEvent(@RequestBody FlightTakenoffEvent subscriptionRequest) {

    }
}
