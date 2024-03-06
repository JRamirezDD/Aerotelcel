package com.notifications_handler.controller.FlightEventReceiver;


import com.flightdata_handler.events.FlightModifiedEvent.FlightDelayedEvent;
import com.flightdata_handler.events.FlightModifiedEvent.FlightLandedEvent;
import com.flightdata_handler.events.FlightModifiedEvent.FlightTakenoffEvent;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/notifications-handler/flight_event")
public interface API_FlightEventReceiver {
    @PostMapping("/delayed")
    @ResponseStatus(HttpStatus.OK)
    void delayedEvent(@RequestBody FlightDelayedEvent subscriptionRequest);

    @PostMapping("/landed")
    @ResponseStatus(HttpStatus.OK)
    void landedEvent(@RequestBody FlightLandedEvent subscriptionRequest);

    @PostMapping("/takenoff")
    @ResponseStatus(HttpStatus.OK)
    void takenoffEvent(@RequestBody FlightTakenoffEvent subscriptionRequest);
}
