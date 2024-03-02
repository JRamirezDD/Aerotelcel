package com.flightdata_handler.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flightdata_handler.model.InAirport;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@Slf4j
public class ReadAirportDepartures implements ServiceInterface {

    //@Autowired
    //private AirportRepository airportRepository;

    @Value("${python.file.airportDepartures}")
    private String pathToPython;
    Process process;
    BufferedReader reader;
    String line;
    ProcessBuilder processBuilder;

    private List<String> departuresFromPython;
    private List<InAirport> departures;

    StringBuilder output;
    boolean jsonStart;
    public boolean valid;

    @Autowired
    private ObjectMapper objectMapper = new ObjectMapper();

    private String ICAOtoLook;

    @Override
    public void doSearch() throws Exception {
        log.info("Searching for all departures at: " + this.ICAOtoLook + "\n");

        boolean finished = false;

        try {
            finished = readPython();
        } catch (IOException e) {
            log.error("Error while reading python file: " + e);
        }

        if(finished){
            turnIntoDepartures(this.departuresFromPython);
        } else {
            log.error("Error while reading python file");
            throw new Exception();
        }

        valid = checkData(this.departures);
    }

    public boolean checkData(List<InAirport> data){
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

    public void turnIntoDepartures(List<String> dataRead) throws JsonProcessingException {
        departures = new ArrayList<InAirport>();

        log.info("Assigning flights as JSON departures\n");

        output = new StringBuilder();
        jsonStart = false;

        for(String s: dataRead){
            // Close the JSON
            if(s.charAt(s.length()-1) == '}'){
                output.append(s);
                jsonStart = false;
                InAirport flight = objectMapper.readValue(output.toString(), InAirport.class);

                departures.add(flight);
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

        log.info("Departures(JSON) conversion finished\n" + departures.size() + " departures were created\n");
    }

    @Override
    public boolean readPython() throws IOException {
        log.info("Searching for all departures at: " + this.ICAOtoLook + "\n");

        try{
            String[] cmd = new String[2];
            cmd[0] = "python"; // should be python3 on some systems
            cmd[1] = pathToPython;

            processBuilder = new ProcessBuilder(cmd);
            process = processBuilder.start();

            Runtime rt = Runtime.getRuntime();
            Process pr = rt.exec(cmd);

            BufferedWriter stdOutput = new BufferedWriter(new OutputStreamWriter(pr.getOutputStream()));
            stdOutput.write(this.ICAOtoLook);
            stdOutput.newLine();
            stdOutput.flush();

            BufferedReader stdInput = new BufferedReader(new InputStreamReader(pr.getInputStream()));

            boolean dataFound = false;

            while(!dataFound){
                if((line = stdInput.readLine()).equals("No_data")) {
                    System.out.println("No data has been received from departures, trying again");

                    pr = rt.exec(cmd);
                    stdInput = new BufferedReader(new InputStreamReader(pr.getInputStream()));
                    stdOutput = new BufferedWriter(new OutputStreamWriter(pr.getOutputStream()));

                    stdOutput.write(this.ICAOtoLook);
                    stdOutput.newLine();
                    stdOutput.flush();

                } else {
                    System.out.println("Data found!!!");

                    while ((line = stdInput.readLine()) != null) {
                        departuresFromPython.add(line);
                    }

                    dataFound = true;
                }
            }

        } catch (Exception e) {
            log.error("Error while reading python file at reading departures: " + e);
            return false;

        }

        // If nothing was read, or an exception was thrown, return false
        log.info("Arrivals read from python file, returning for JSON conversion\n");
        return true;
    }

    public List<InAirport> getDepartures(){
        return departures;
    }

    @Override
    public String getPythonPath() {
        return this.pathToPython;
    }
}
