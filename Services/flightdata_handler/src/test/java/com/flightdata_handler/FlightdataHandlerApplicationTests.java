package com.flightdata_handler;

import com.flightdata_handler.model.Flight;
import com.flightdata_handler.service.ReadAllStates;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
public class FlightdataHandlerApplicationTests {
    @MockBean
    private ReadAllStates readAllStates;

    @Value("${python.file.flightTest}")
    private String pathToPython;

    List<String> statesFromPython = new ArrayList<>();

    String flightBit1 = "{  'baro_altitude': 7848.6,";
    String flightBit2 = "   'callsign': 'ACA1074 ',";
    String flightBit3 = "   'category': 0,";
    String flightBit4 = "   'geo_altitude': 7688.58,";
    String flightBit5 = "   'icao24': 'c067ae',";
    String flightBit6 = "   'last_contact': 1710277134,";
    String flightBit7 = "   'latitude': 46.1471,";
    String flightBit8 = "   'longitude': -75.5616,";
    String flightBit9 = "   'on_ground': False,";
    String flightBit10 = "   'origin_country': 'Canada',";
    String flightBit11 = "   'position_source': 0,";
    String flightBit12 = "   'sensors': None,";
    String flightBit13 = "   'spi': False,";
    String flightBit14 = "   'squawk': '5236',";
    String flightBit16 = "   'time_position': 1710277134,";
    String flightBit17 = "   'true_track': 87.96,";
    String flightBit18 = "   'velocity': 231.13,";
    String flightBit19 = "   'vertical_rate': -9.1 }";

    @Test
    public void testFlightCreation() throws IOException {
        System.out.println("Testing flight creation");
        System.out.println("Path to python: " + pathToPython);

        statesFromPython.add(flightBit1);
        statesFromPython.add(flightBit2);
        statesFromPython.add(flightBit3);
        statesFromPython.add(flightBit4);
        statesFromPython.add(flightBit5);
        statesFromPython.add(flightBit6);
        statesFromPython.add(flightBit7);
        statesFromPython.add(flightBit8);
        statesFromPython.add(flightBit9);
        statesFromPython.add(flightBit10);
        statesFromPython.add(flightBit11);
        statesFromPython.add(flightBit12);
        statesFromPython.add(flightBit13);
        statesFromPython.add(flightBit14);
        statesFromPython.add(flightBit16);
        statesFromPython.add(flightBit17);
        statesFromPython.add(flightBit18);
        statesFromPython.add(flightBit19);

        when(readAllStates.readPython(pathToPython)).thenReturn(true);
        when(readAllStates.getStatesFromPython()).thenReturn(statesFromPython);

        boolean finished = false;

        // Test the creation of a flight by reading a pre-written python script
        try {
            finished = readAllStates.readPython(pathToPython);

        } catch (Exception e) {
            System.out.println("Error while creating flight: " + e);
        }

        System.out.println("Python script read: " + finished);

        List<Flight> uncheckedFlightList;

        if (finished) {
            statesFromPython = readAllStates.getStatesFromPython();
            System.out.println("States from python: " + statesFromPython);

            try {
                uncheckedFlightList = readAllStates.testTurnIntoFlight(statesFromPython);
                System.out.println("Flight created" + uncheckedFlightList.get(0));

                for(Flight flight : uncheckedFlightList) {
                    System.out.println(flight);
                }

            } catch (Exception e) {
                System.out.println("Error while creating flight: " + e);
            }

        } else {
            System.out.println("Flight creation test failed");
        }

    }
}
