package com.flightdata_retriever.controller;


import com.flightdata_handler.dto.AirportResponse;
import com.flightdata_retriever.service.RetrieveAirportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/airportDataRetriever")
@RequiredArgsConstructor
@Slf4j
public class AirportDataRetriever {
    private final RetrieveAirportService retrieveAirports;

    @RequestMapping("/")
    public String home(){
        return "This is AirportDataRetriever";
    }

    @RequestMapping("/updateAirports")
    public void updateAirports() throws Exception {
        retrieveAirports.updateAllAirports();
    }

    @RequestMapping("/updateSelectedAirport/{icao}")
    public void updateSelectedAirport(String iata) throws Exception {
        retrieveAirports.updateSelectedAirport(iata);
    }

    @RequestMapping("/getAirports")
    public List<AirportResponse> getAirports() {
        return retrieveAirports.getAllAirports();
    }

    @RequestMapping("/getAirportByCode/{iata}")
    public AirportResponse getAirportByCode(String iata) {
        return retrieveAirports.getAirportByCode(iata);
    }


}
