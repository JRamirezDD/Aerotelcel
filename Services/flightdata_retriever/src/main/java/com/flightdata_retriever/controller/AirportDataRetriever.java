package com.flightdata_retriever.controller;


import com.flightdata_handler.dto.AirportResponse;
import com.flightdata_retriever.service.RetrieveAirportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/airportDataRetriever")
@RequiredArgsConstructor
@Slf4j
public class AirportDataRetriever {
    private final RetrieveAirportService retrieveAirports;

    @RequestMapping("/")
    public String home(){
        return retrieveAirports.home();
    }

    @RequestMapping("/updateAirports")
    @ResponseStatus(HttpStatus.OK)
    public void updateAirports() throws Exception {
        retrieveAirports.updateAllAirports();
    }

    @RequestMapping("/updateSelectedAirport/{icao}")
    @ResponseStatus(HttpStatus.OK)
    public void updateSelectedAirport(@PathVariable String iata) throws Exception {
        retrieveAirports.updateSelectedAirport(iata);
    }

    @RequestMapping("/getAirports")
    @ResponseStatus(HttpStatus.OK)
    public List<AirportResponse> getAirports() {
        return retrieveAirports.getAllAirports();
    }

    @RequestMapping("/getAirportByCode/{iata}")
    @ResponseStatus(HttpStatus.OK)
    public AirportResponse getAirportByCode(@PathVariable String iata) {
        return retrieveAirports.getAirportByCode(iata);
    }


}
