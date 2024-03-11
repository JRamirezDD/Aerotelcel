package com.flightdata_retriever.service;

import com.flightdata_handler.dto.FlightDataResponse;
import com.flightdata_handler.dto.FlightResponse;
import com.flightdata_retriever.service.FeignClients.RetrieveFlights;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RetrieveFlightsService {
    @Autowired
    final RetrieveFlights retrieveFlights;

    public String home() {
        return retrieveFlights.home();
    }

    public void updateFlights() throws Exception {
        retrieveFlights.updateAllStates();
    }

    public List<FlightResponse> getFlights() {
        return retrieveFlights.getAllFlights();
    }

    public FlightDataResponse getFlightByCallsign(String callsign) {
        return retrieveFlights.getFlightByCallsign(callsign);
    }


}
