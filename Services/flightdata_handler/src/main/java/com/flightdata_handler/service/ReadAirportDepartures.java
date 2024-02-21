package com.flightdata_handler.service;

import com.flightdata_handler.model.Flight;
import com.flightdata_handler.repository.AirportRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@Service
@Transactional
@Slf4j
public class ReadAirportDepartures implements ServiceInterface {

    @Autowired
    private AirportRepository airportRepository;

    @Value("${python.file.airportDepartures}")
    private String pathToPython;
    Process process;
    BufferedReader reader;
    String line;
    ProcessBuilder processBuilder;

    private List<String> departuresFromPython;
    private List<Flight> departures;

    StringBuilder output;
    boolean jsonStart;

    private String ICAOtoLook;

    public ReadAirportDepartures() {
        log.info("ReadAirportDepartures Service Started");
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

        return false;
    }

    public List<Flight> getDepartures(){
        return departures;
    }

    @Override
    public String getPythonPath() {
        return this.pathToPython;
    }
}
