package com.flightdata_handler.service;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.flightdata_handler.dto.FlightResponse;
import com.flightdata_handler.events.FlightModifiedEvent.FlightDelayedEvent;
import com.flightdata_handler.events.FlightModifiedEvent.FlightLandedEvent;
import com.flightdata_handler.events.FlightModifiedEvent.FlightTakenoffEvent;
import com.flightdata_handler.model.Airline;
import com.flightdata_handler.model.Flight;
import com.flightdata_handler.model.InAirport;
import com.flightdata_handler.model.enums.FlightStatusEnum;
import com.flightdata_handler.repository.FlightRepository;
import com.flightdata_handler.repository.AirlineRepository;
import com.flightdata_handler.repository.InAirportRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
// Gets all flights from OpenSky with a python file using "Get all states" function
public class ReadAllStates implements ServiceInterface {
    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private AirlineRepository airlineRepository;

    @Autowired
    private InAirportRepository inAirportRepository;

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

        long start = System.currentTimeMillis();
        try {
            finished = readPython();
        } catch (IOException e) {
            long errorEnd = System.currentTimeMillis();
            long errorTime = errorEnd - start;
            log.error("Error while reading python file: " + e + " After: " + errorTime + "ms\n");
        }

        long end = System.currentTimeMillis();
        long time = end - start;
        log.info("Time to read python file: " + time + "ms\n");


        start = System.currentTimeMillis();
        if(finished){
            turnIntoFlight(this.statesFromPython);

        } else {
            log.error("No data was read from the python file\n");
            long errorEnd = System.currentTimeMillis();
            long errorTime = errorEnd - start;
            log.error("Error while turning into flight. After: " + errorTime + "ms\n");
            throw new Exception();
        }

        end = System.currentTimeMillis();
        time = end - start;
        log.info("Time to turn into flight: " + time + "ms\n");

        log.info("Assigning airlines\n");
        start = System.currentTimeMillis();
        // Assign airline to flights
        for(Flight flight : this.dataToUpload){
            if(flight.getAirline() == null && flight.getCallsign() != null){
                assignAirline(flight);
            }
        }
        end = System.currentTimeMillis();
        time = end - start;
        log.info("Time to assign airlines: " + time +  "ms\n");

        // Check for delay
        log.info("Checking for delay");
        start = System.currentTimeMillis();
        List<Flight> delayVerification = getRidOfOutliers(this.dataToUpload);
        end = System.currentTimeMillis();
        time = end - start;
        log.info("Time to check for delay: " + time + " ms\n");

        start = System.currentTimeMillis();
        log.info("Validating Data");
        List<Flight> validData = checkData(delayVerification);
        end = System.currentTimeMillis();
        time = end - start;
        log.info("Time to validate data: " + time + " ms\n");

        if(validData != null && !validData.isEmpty()){
            log.info("Data is valid, uploading\n");
            start = System.currentTimeMillis();
            uploadData(validData);
            end = System.currentTimeMillis();
            time = end - start;

        } else {
            long errorEnd = System.currentTimeMillis();
            long errorTime = errorEnd - start;
            log.error("Data is not valid, no data was uploaded. After: " + errorTime + " ms. \n");
        }
    }

    public void assignAirline(Flight flight){
        //log.info("Getting airline for " + flight.getCallsign() + "\n");

        if(flight.getCallsign() == null || flight.getCallsign().isEmpty()){
            //log.error("Callsign is null\n");
            return;
        }

        // Get airline from callsign
        String icao = flight.getCallsign();

        String airlineIcao = icao.substring(0, 3);

        if(airlineIcao == null || airlineIcao.equals("")){
            log.error("Callsign is null\n");
            return;
        }

        //log.info("Extracted Airline code: " + airlineIcao + "\n");

        // Get airline from DB
        Airline airline = airlineRepository.findById(airlineIcao).orElse(null);

        if(airline == null){
           //log.error("Airline not found for " + flight + "\n");

        } else {
            //log.info("Airline found: " + airline.getAirline() + "\n");
            flight.setAirline(airline.getAirline());

        }

    }

    @Transactional
    public synchronized void uploadData(List<Flight> dataToUpload) {
        // Set Flight Status
        for (Flight flight: dataToUpload) {
            Optional<Flight> oldFlightOptional = flightRepository.findById(flight.getCallsign());

            // Update Flight Status
            if (oldFlightOptional.isPresent()) {
                Flight oldFlight = oldFlightOptional.get();
                // Set new Status
                flight.setStatus(flight.statusLogic(oldFlight));

                // Publish Events where required
                if (flight.getStatus() == FlightStatusEnum.Taken_Off){ eventPublisher.publishEvent(new FlightTakenoffEvent(this, flight)); }
                else if (flight.getStatus() == FlightStatusEnum.Landed){ eventPublisher.publishEvent(new FlightLandedEvent(this, flight)); }
                else if (flight.isArrivalDelayed() == true) { eventPublisher.publishEvent(new FlightDelayedEvent(this, flight, oldFlight.getETA())); }
                else if (flight.isDepartureDelayed() == true) { eventPublisher.publishEvent(new FlightDelayedEvent(this, flight, oldFlight.getETD())); }
            } else {

                flight.setStatus(flight.isOn_ground() ? FlightStatusEnum.On_Ground : FlightStatusEnum.Flying);
            }
        }

        log.info("Established status successfully");
        flightRepository.saveAll(dataToUpload);
    }

    public List<Flight> checkData(List<Flight> dataToUpload){
        if(dataToUpload == null){
            log.error("Data is null");
            return null;

        } else if(dataToUpload.isEmpty()){
            log.error("Data is empty");
            return null;

        } else if (!dataToUpload.isEmpty()) {
            List<Flight> validatedData = new ArrayList<Flight>();

            for(Flight flight : dataToUpload){
                if(flight.getCallsign() != null && !flight.getCallsign().isEmpty() && !flight.getCallsign().equals("")){

                    flight.setLastTimeUpdated(new Timestamp(System.currentTimeMillis()));

                    validatedData.add(flight);
                }
            }

            return validatedData;
        }

        return null;
    }

    public List<Flight> getRidOfOutliers (List<Flight> beforeCheck){
        List<Flight> afterCheck = new ArrayList<Flight>();

        // First check for delay in InAirport Repository
        /*for (Flight flight: beforeCheck){
            Optional<InAirport> inAirportOpt = inAirportRepository.findById(flight.getCallsign());

            if (inAirportOpt.isPresent()){
                InAirport inAirport = inAirportOpt.get();

                if (inAirport.getType().equals("departure") ) {

                    flight.setETD(inAirport.getDepartureDelay(flight));

                } else if (inAirport.getType().equals("arrival")) {

                    flight.setETA(inAirport.getArrivalDelay(flight));

                }
            }
        }*/

        int flightsWithNoCallsign = 0;

        for (Flight flight: beforeCheck){
            Optional<Flight> checkOldFlight = flightRepository.findById(flight.getCallsign());

            if (!flight.getCallsign().isEmpty() && !checkOldFlight.isPresent()){
                afterCheck.add(flight);
            } else if (!flight.getCallsign().isEmpty() && checkOldFlight.isPresent()){
                Flight oldFlight = checkOldFlight.get();

                // Checks if the flight is older than a day
                if(oldFlight.getLastTimeUpdated() != null && oldFlight.getLastTimeUpdated().getTime() < System.currentTimeMillis() - 24 * 60 * 60 * 1000){
                    log.info("Flight " + flight.getIcao24() + " has not been updated in the last 24 hours, kicking out... \n");
                    flightRepository.delete(oldFlight);
                    continue;
                } else {
                    log.info("Flight " + flight.getIcao24() + " has been updated in the last 24 hours, updating... \n");
                    updateFlight(flight, oldFlight);
                }

            } else {
                flightsWithNoCallsign++;
            }
        }

        log.info("Flights with no callsign: " + flightsWithNoCallsign + "\n");
        return afterCheck;
    }

    public void turnIntoFlight(List<String> statesFromPython) throws JsonProcessingException {
        // Flight list
        dataToUpload = new ArrayList<Flight>();

        // Allow single quotes
        objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);

        log.info("Flight(JSON) conversion starting\n");

        output = new StringBuilder();
        jsonStart = false;

        for(String s : statesFromPython){
            // Replace capitalized boolean values with lowercase ones
            s = s.replace("True", "true").replace("False", "false").replace("None", "null");

            // Close the JSON object
            if(s.charAt(s.length()-1) == '}') {
                output.append(s);
                jsonStart = false;

                Flight beingRead = objectMapper.readValue(output.toString(), Flight.class);

                dataToUpload.add(beingRead);
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
            log.info("ProcessBuilder started, path to file" + pathToPython + "\n");
            process = processBuilder.start();

            //int exitCode = process.waitFor();

            //log.info("\nExited with error code : " + exitCode);

        } catch (Exception e) {
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

    public List<FlightResponse> getFlights(){
        return turnIntoResponse(flightRepository.findAll());
    }

    public List<FlightResponse> turnIntoResponse(List<Flight> flights){
        List<FlightResponse> response = new ArrayList<FlightResponse>();

        for(Flight flight : flights){
            response.add(new FlightResponse(flight));
        }

        return response;
    }

    public void updateFlight(Flight flight, Flight oldFlight){
        // log.info("Flight: " + flight.getCallsign() + " already in database, updating... \n");

        if(flight.getStatus() != null){
            flight.setStatus(flight.statusLogic(oldFlight));
        }

        flight.setLastTimeUpdated(new Timestamp(System.currentTimeMillis()));

        flightRepository.save(flight);
    }

    public FlightResponse getUniqueFlight(String callsign){
        return new FlightResponse(flightRepository.getReferenceById(callsign));
    }

    public void cleanDB(){
        flightRepository.deleteAll();
    }


    @Override
    public String getPythonPath() {
        return this.pathToPython;
    }
}
