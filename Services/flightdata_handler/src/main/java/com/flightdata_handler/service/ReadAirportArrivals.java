package com.flightdata_handler.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.flightdata_handler.model.Arrivals;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@Slf4j
public class ReadAirportArrivals implements ServiceInterface {

    // Variables to read file
    // @Value("${python.file.airportArrivals}")
    private final String pathToPython = "Services/flightdata_handler/src/main/pythonFiles/airportArrivals.py";
    Process process;
    BufferedReader reader;
    BufferedWriter writer;
    String line;
    ProcessBuilder processBuilder;

    private List<String> arrivalsFromPython;
    private List<Arrivals> arrivals;

    StringBuilder output;
    boolean jsonStart;

    // Enables overall manager to retrieve all results
    public boolean valid;

    @Autowired
    private ObjectMapper objectMapper = new ObjectMapper();

    private String ICAOtoLook;

    @Override
    public void doSearch() throws Exception {
        log.info("Searching for all arrivals at: " + this.ICAOtoLook + "\n");

        boolean finished = false;

        try {
            finished = readPython();
        } catch (IOException e) {
            log.error("Error while reading python file: " + e);
        }

        if(finished){
            turnIntoArrivals(this.arrivalsFromPython);
        } else {
            log.error("Error while reading python file");
            throw new Exception();
        }

        valid = checkData(this.arrivals);
    }

    public boolean checkData(List<Arrivals> data){
        if(data == null){
            log.error("Data is null");
            return false;
        } else if(data.isEmpty()){
            log.error("Data is empty");
            return false;
        } else {
            log.info("Data is valid");
            return true;
        }
    }

    public void setAirportCode(String airportCode){
        this.ICAOtoLook = airportCode;
    }

    public void turnIntoArrivals(List<String> dataRead) throws JsonProcessingException {
        arrivals = new ArrayList<Arrivals>();

        // Allow single quotes
        objectMapper.configure(com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);

        log.info("Assigning flights as JSON arrivals\n");

        output = new StringBuilder();
        jsonStart = false;

        for(String s: dataRead){
            s = s.replace("True", "true").replace("False", "false").replace("None", "null");

            // Close the JSON
            if(s.charAt(s.length()-1) == '}'){
                output.append(s);
                jsonStart = false;

                // Transform timestamps before making Arrivals
                JsonNode jsonNode = objectMapper.readTree(output.toString());

                int departureTime = jsonNode.get("lastSeen").asInt();
                int arrivalTime = jsonNode.get("firstSeen").asInt();

                Timestamp departureDate = convertUnixToTimestamp(departureTime);
                Timestamp arrivalDate = convertUnixToTimestamp(arrivalTime);

                ((ObjectNode) jsonNode).put("lastSeen", departureDate.getTime());
                ((ObjectNode) jsonNode).put("firstSeen", arrivalDate.getTime());

                String json = objectMapper.writeValueAsString(jsonNode);

                Arrivals flight = objectMapper.readValue(json, Arrivals.class);

                Arrivals existingFlight = arrivals.stream()
                        .filter(a -> a.getCallsign().equals(flight.getCallsign()))
                        .findFirst()
                        .orElse(null);

                if(existingFlight != null){
                    existingFlight.updateFrom(flight);
                } else {
                    arrivals.add(flight);
                }

                output = new StringBuilder();

            } else if(jsonStart){
                output.append(s);

            }

            // Open the JSON
            if(s.contains("{") && !jsonStart){
                jsonStart = true;
                output.append(s);

            }
        }

        log.info("Arrivals(JSON) conversion finished\n" + arrivals.size() + " arrivals were created\n");
    }

    @Override
    public boolean readPython() throws IOException {
        log.info("Searching for all arrivals at: " + this.ICAOtoLook + "\n");

        int attempts = 0;

        try{
            log.info("Reading python file for arrivals from\n");

            processBuilder = new ProcessBuilder("python", pathToPython);

            process = processBuilder.start();

            log.info("Declared process, attempting read");

            writer = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));
            writer.write(this.ICAOtoLook);
            writer.newLine();
            writer.flush();

            log.info("Python file executed, reading departures\n");

            reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            log.info("Reading arrivals from python file\n");

            while((line = reader.readLine()) != null){

                if(line.equals("No_data") && attempts < 3){
                    log.info("No data has been received from arrivals, trying again. Attempt: " + attempts + "\n");

                    attempts++;

                    if(attempts == 3){
                        log.error("No data has been received from departures, tried 3 times, returning false");
                        return false;
                    }

                    process = processBuilder.start();
                    reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                    writer = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));

                    writer.write(this.ICAOtoLook);
                    writer.newLine();
                    writer.flush();

                } else if(line.equals("Traceback (most recent call last):")){
                    log.error("Error while reading python file at reading arrivals. Attempt #"+ attempts + ". Error code: " + line);
                    throw new IOException();

                } else {
                    //log.info("Data found: " + line + "\n");
                    if(arrivalsFromPython == null){arrivalsFromPython = new ArrayList<String>();}
                    arrivalsFromPython.add(line);
                }
            }

        } catch (Exception e) {
            log.error("Error while reading python file at reading arrivals. Attempt #"+ attempts + ". Error code: " + e);
            return false;

        }

        // If nothing was read, or an exception was thrown, return false
        log.info("Arrivals read from python file, returning for JSON conversion\n");
        return true;
    }

    public Timestamp convertUnixToTimestamp(int unixTime){
        long milisecs = unixTime * 1000L;
        return new Timestamp(milisecs);
    }

    public void clearInfo(){
        this.arrivals = null;
        this.arrivalsFromPython = null;
    }

    public List<Arrivals> getArrivals(){
        return arrivals;
    }

    @Override
    public String getPythonPath(){
        return this.pathToPython;
    }


}
