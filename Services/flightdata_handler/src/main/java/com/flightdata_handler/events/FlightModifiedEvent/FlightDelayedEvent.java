package com.flightdata_handler.events.FlightModifiedEvent;

import com.flightdata_handler.model.Flight;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
public class FlightDelayedEvent extends FlightModifiedEvent{
    final Timestamp oldTime;
    final Timestamp newTime;

    public FlightDelayedEvent(Object source, Flight flight, Timestamp oldTime){
        super(source, flight);
        this.oldTime = oldTime;
        this.newTime = flight.getETA();
    }
}
