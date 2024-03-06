package com.flightdata_handler.events.FlightModifiedEvent;

import com.flightdata_handler.model.Flight;

import java.sql.Timestamp;

public class FlightDelayedEvent extends FlightModifiedEvent{
    final Timestamp oldTime;

    public FlightDelayedEvent(Object source, Flight flight, Timestamp oldTime){
        super(source, flight);
        this.oldTime = oldTime;
    }
}
