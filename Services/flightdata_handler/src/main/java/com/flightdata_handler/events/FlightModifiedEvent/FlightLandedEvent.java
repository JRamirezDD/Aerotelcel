/*
 *    Title: Source Code
 *    Author: Ramirez de Diego, Jorge
 *    Date: 2024
 *    Code version: 1.0
 *    Availability: https://github.com/JRamirezDD/Aerotelcel
 */


package com.flightdata_handler.events.FlightModifiedEvent;

import com.flightdata_handler.model.Flight;

public class FlightLandedEvent extends FlightModifiedEvent{
    public FlightLandedEvent(Object source, Flight flight ){
        super(source, flight);
    }
}
