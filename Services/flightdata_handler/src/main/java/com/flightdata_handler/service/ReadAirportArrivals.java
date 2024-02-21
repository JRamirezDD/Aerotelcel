package com.flightdata_handler.service;

import com.flightdata_handler.model.Flight;
import com.flightdata_handler.repository.AirportRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ReadAirportArrivals implements ServiceInterface {
    @Autowired
    private AirportRepository airportRepository;

    // Vairables to read file
    @Value("${python.file.airportArrivals}")
    private String pathToPython;
    Process process;
    BufferedReader reader;
    String line;
    ProcessBuilder processBuilder;

    private List<String> arrivalsFromPython;
    private List<Flight> arrivals;

    StringBuilder output;
    boolean jsonStart;


    private String ICAOtoLook;

    public ReadAirportArrivals(){
        log.info("ReadAirportArrivals Service Started");
    }

    @Override
    public void doSearch() throws Exception {
        readPython();
    }

    public void setAirportCode(String airportCode){
        this.ICAOtoLook = airportCode;
    }

    @Override
    public boolean readPython() throws IOException {
        // Write method n shit

        arrivals = new ArrayList<Flight>();

        return false;
    }

    public List<Flight> getArrivals(){
        return arrivals;
    }

    @Override
    public String getPythonPath(){
        return this.pathToPython;
    }


}
