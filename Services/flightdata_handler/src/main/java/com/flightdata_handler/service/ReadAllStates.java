package com.flightdata_handler.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.flightdata_handler.model.Airline;
import com.flightdata_handler.model.Flight;
import com.flightdata_handler.repository.FlightRepository;
import com.flightdata_handler.repository.AirlineRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@Slf4j
// Gets all flights from OpenSky with a python file using "Get all states" function
public class ReadAllStates implements ServiceInterface {
    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private AirlineRepository airlineRepository;

    // Variables to read file
    @Value("${python.file.getAllFlights}")
    private String pathToPython;                    // Path to python file - edit @ application.properties
    Process process;
    BufferedReader reader;
    String line;
    ProcessBuilder processBuilder;

    private List<String> statesFromPython;
    private List<Flight> dataToUpload;

    // Variables to turn data into JSON
    StringBuilder output;
    boolean jsonStart;

    @Autowired
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void doSearch() throws Exception {
        log.info("Searching for all flights\n");

        boolean finished = false;

        try {
            finished = readPython();
        } catch (IOException e) {
            log.error("Error while reading python file: " + e);
        }

        if(finished){
            turnIntoFlight(this.statesFromPython);

        } else {
            log.error("No data was read from the python file\n");
            throw new Exception();
        }

        // Assign airline to flights
        for(Flight flight : this.dataToUpload){
            if(flight.getAirline() == null && flight.getCallsign() != null){
                assignAirline(flight);
            }
        }

        boolean valid = checkData(this.dataToUpload);

        if(valid){
            log.info("Data is valid, uploading\n");
            uploadData(this.dataToUpload);

        } else {
            log.error("Data is not valid, no data was uploaded\n");
            throw new Exception();
        }
    }

    public void assignAirline(Flight flight){
        log.info("Getting airline for " + flight.getCallsign() + "\n");

        // Get airline from callsign
        String icao = flight.getCallsign();

        String airlineIcao = icao.substring(0, 3);

        // Get airline from DB
        Airline airline = airlineRepository.findById(airlineIcao).orElse(null);
        flight.setAirline(airline.getAirline());

    }

    public void uploadData(List<Flight> dataToUpload) {
        flightRepository.saveAll(dataToUpload);
    }

    public boolean checkData(List<Flight> dataToUpload){
        if(dataToUpload == null){
            log.error("Data is null");
            return false;

        } else if(dataToUpload.isEmpty()){
            log.error("Data is empty");
            return false;

        } else {
            log.info("Data is valid");
            return true;

        }
    }

    public void turnIntoFlight(List<String> statesFromPython) throws JsonProcessingException {
        // Flight list
        dataToUpload = new ArrayList<Flight>();

        log.info("Flight(JSON) conversion starting\n");

        output = new StringBuilder();
        jsonStart = false;

        for(String s : statesFromPython){

            // Close the JSON object
            if(s.charAt(s.length()-1) == '}') {
                output.append(s);
                jsonStart = false;
                Flight flightObject = objectMapper.readValue(output.toString(), Flight.class);

                dataToUpload.add(flightObject);
                output = new StringBuilder();

            } else if (jsonStart) {
                output.append(s);

            }

            // Open the JSON object
            if (s.contains("{") && !jsonStart) {
                jsonStart = true;
                output.append(s);
            }
        }

        log.info("Flight(JSON) conversion finished\n" + dataToUpload.size() + " flights were created\n");
    }

    // Returns all read flights as a list of JSONs
    @Override
    public boolean readPython() throws IOException {
        log.info("Reading python file\n");

        try {
            processBuilder = new ProcessBuilder("python", pathToPython);       // this.pathToPython
            process = processBuilder.start();

            int exitCode = process.waitFor();

            log.info("\nExited with error code : " + exitCode);

        } catch (InterruptedException e) {
            log.error("Error while waiting for process to finish: " + e);
            return false;
        }

        reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

        // Read the file and store it in a list
        statesFromPython = new ArrayList<String>();

        while((line = reader.readLine()) != null){
            statesFromPython.add(line);
        }

        if(statesFromPython.isEmpty()){
            log.info("No data was read from the python file\n");
            return false;
        }

        log.info("File has been fully read, returning for Flight(JSON) conversion with " + statesFromPython.size() + " lines\n");
        return true;
    }

    @Override
    public String getPythonPath() {
        return this.pathToPython;
    }
}
