package com.flightdata_handler.service;

import com.flightdata_handler.model.Flight;
import com.flightdata_handler.repository.AirportRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.sql.Connection;
import java.util.List;

@Service
@Slf4j
public class ReadAirportArrivals extends FetchFlights {
    @Autowired
    private AirportRepository airportRepository;

    // Vairables to read file
    @Value("${python.file.airportArrivals}")
    private String pathToPython;

    private String ICAOtoLook;

    Process process;
    BufferedReader reader;
    String line;
    ProcessBuilder processBuilder;

    private List<Flight> arrivals;
    StringBuilder output;
    boolean jsonStart;
    private List<Flight> dataToUpload;

    public ReadAirportArrivals(){

        this.pathToFile = pathToPython;
    }

    public ReadAirportArrivals(Connection conn){
        super(conn);

        this.pathToFile = pathToPython;
    }

    public void setICAOtoLook(String icaoCode){
        this.ICAOtoLook = icaoCode;
    }

    @Override
    public List<Flight> readPython(String airportCode) {

        return arrivals;
    }
}
