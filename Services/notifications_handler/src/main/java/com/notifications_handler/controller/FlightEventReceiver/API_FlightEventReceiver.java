package com.notifications_handler.controller.FlightEventReceiver;


import com.flightdata_handler.events.FlightModifiedEvent.FlightDelayedEvent;
import com.flightdata_handler.events.FlightModifiedEvent.FlightLandedEvent;
import com.flightdata_handler.events.FlightModifiedEvent.FlightTakenoffEvent;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/notifications-handler/flightEvent")
public interface API_FlightEventReceiver {

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    String home();

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
