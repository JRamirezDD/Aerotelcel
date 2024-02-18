package com.flightdata_handler.service;

import com.flightdata_handler.model.Flight;
import com.flightdata_handler.repository.FlightRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@Slf4j
// Gets all flights from OpenSky with a python file using "Get all states" function
public class ReadAllStates implements ServiceInterface {
    @Autowired
    private FlightRepository flightRepository;

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
        readPython();
    }

    // Returns all read flights as a JSON list
    @Override
    public String readPython() throws IOException {
        log.info("Reading python file\n");

        processBuilder = new ProcessBuilder("python", this.pathToPython);
        process = processBuilder.start();

        try {
            int exitCode = process.waitFor();
            log.info("\nExited with error code : " + exitCode);
        } catch (InterruptedException e) {
            log.error("Error while waiting for process to finish: " + e);
        }

        reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

        statesFromPython = new ArrayList<String>();
        dataToUpload = new ArrayList<Flight>();

        output = new StringBuilder();
        jsonStart = false;

        while((line = reader.readLine()) != null){
            statesFromPython.add(line);
        }

        if(statesFromPython.isEmpty()){
            log.info("No data was read from the python file\n");
            return null;
        }

        log.info("File has been fully read, Flight(JSON) conversion starting\n");

        for(String s : statesFromPython){
            if(s.charAt(s.length()-1) == '}') {
                output.append(s);
                jsonStart = false;
                Flight flightObject = objectMapper.readValue(output.toString(), Flight.class);
                //Flight flightObject = new Flight(output.toString());

                dataToUpload.add(flightObject);
                output = new StringBuilder();
            } else if (jsonStart) {
                output.append(s);
            }

            if (s.contains("{") && !jsonStart) {
                jsonStart = true;
                output.append(s);
            }
        }

        if(dataToUpload.isEmpty()){
            log.info("No Flight Objects were created\n");
            return null;
        }

        flightRepository.saveAll(dataToUpload);

        log.info("Flight's ready and returning\n");

        return "Done";
    }

    @Override
    public String getPythonPath() {
        return this.pathToPython;
    }
}
