package com.flightdata_handler.events.FlightModifiedEvent;

import com.flightdata_handler.model.Flight;

public class FlightTakenoffEvent extends FlightModifiedEvent {
    public FlightTakenoffEvent(Object source, Flight flight ){
        super(source, flight);
    }
}
