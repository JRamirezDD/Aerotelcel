package com.flightdata_handler.events;

import com.flightdata_handler.model.Airport;
import org.springframework.context.ApplicationEvent;

public class AirportModifiedEvent extends ApplicationEvent {
    private final Airport airport;

    public AirportModifiedEvent(Object source, Airport airport) {
        super(source);
        this.airport = airport;
    }

    public Airport getAirport() {
        return airport;
    }
}
