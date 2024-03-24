/*
 *    Title: Source Code
 *    Author: Ortega Mendoza, Javier
 *    Date: 2024
 *    Code version: 1.0
 *    Availability: https://github.com/JRamirezDD/Aerotelcel
 */

package com.flightdata_retriever.service;

import com.flightdata_handler.dto.AirportResponse;
import com.flightdata_handler.model.Airport;
import com.flightdata_retriever.service.FeignClients.RetrieveAirport;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class RetrieveAirportService {
    @Autowired
    private RetrieveAirport retrieveAirport;

    public String home() {
        return retrieveAirport.home();
    }

    public void updateAllAirports() throws Exception {
        retrieveAirport.updateAllAirports();
    }

    public void updateSelectedAirport(String icao) throws Exception {
        retrieveAirport.updateSelectedAirport(icao);
    }

    public List<AirportResponse> getAllAirports() {
        return retrieveAirport.getAllAirports();
    }

    public AirportResponse getAirportByCode(String iata) {
        return retrieveAirport.getAirportByCode(iata);
    }
}
