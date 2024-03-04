package com.flightdata_handler.service.FeignClients;


import com.flightdata_handler.events.FlightModifiedEvent.FlightDelayedEvent;
import com.flightdata_handler.events.FlightModifiedEvent.FlightLandedEvent;
import com.flightdata_handler.events.FlightModifiedEvent.FlightTakenoffEvent;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;


// Could not extend API on NotificationsHandler due to circular dependency.
    // Figure out in the long term. Not relevant for project.
@FeignClient(name = "${Aerotelcel.subscription-handler.controller.notifications-handler.name}", url = "${Aerotelcel.notifications-handler.address}:${Aerotelcel.notifications-handler.port}${Aerotelcel.notifications-handler.controller.FlightEventReceiver.path}")
public interface Client_NotificationsHandler_FlightEventReceiver {
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
