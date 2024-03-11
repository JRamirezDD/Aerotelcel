package com.flightdata_handler.controller;

import com.flightdata_handler.dto.AirportResponse;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


//@RequestMapping("/api/airportDataController")
public interface API_AirportDataController {
    @GetMapping("/")
    String home();

    @GetMapping("/updateAllAirports")
    void updateAllAirports() throws Exception;

    @GetMapping("/updateSelectedAirport/{iata}")
    void updateSelectedAirport(@PathVariable String iata) throws Exception;

    @GetMapping("/getAllAirports")
    List<AirportResponse> getAllAirports();

    @GetMapping("/getAirportByCode/{iata}")
    AirportResponse getAirportByCode(@PathVariable String iata);

    @DeleteMapping
    void deleteAllAirports();
}
