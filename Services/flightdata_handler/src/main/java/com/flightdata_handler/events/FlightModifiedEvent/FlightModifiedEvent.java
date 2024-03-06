package com.flightdata_handler.events.FlightModifiedEvent;

import com.flightdata_handler.model.Flight;
import org.springframework.context.ApplicationEvent;

public abstract class FlightModifiedEvent extends ApplicationEvent {
    private final Flight flight;

    public FlightModifiedEvent(Object source, Flight flight) {
        super(source);
        this.flight = flight;
    }

    public Flight getAirport() {
        return flight;
    }
}
