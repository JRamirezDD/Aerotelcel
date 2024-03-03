package com.flightdata_handler.events.FlightModifiedEvent;

import com.flightdata_handler.model.Flight;

public class FlightLandedEvent extends FlightModifiedEvent{
    public FlightLandedEvent(Object source, Flight flight ){
        super(source, flight);
    }
}
