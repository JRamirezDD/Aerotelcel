package com.flightdata_handler.service;

import com.flightdata_handler.model.Flight;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

// Gets all flights from OpenSky with a python file using "Get all states" function
public class ReadAllStates extends FetchFlights {

    // Variables to read file
    Process process;
    BufferedReader reader;
    String line;
    ProcessBuilder processBuilder;

    private List<String> statesFromPython;

    // Variables to turn data into JSON
    StringBuilder output;
    boolean jsonStart;
    private List<Flight> dataToUpload;


    public ReadAllStates(Connection conn){
        super(conn);

        // Replace with actual path in end testing
        this.pathToFile = "C:\\Users\\javie\\Documents\\Code_Man_Laptop\\Aerotelcel\\Services\\flightdata_handler\\src\\main\\pythonFiles\\getAllFlights.py";
    }

    // Returns all read flights as a JSON list
    public List<Flight> readPython() throws Exception {
        processBuilder = new ProcessBuilder("python", this.pathToFile);
        process = processBuilder.start();

        reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

        statesFromPython = new ArrayList<String>();
        dataToUpload = new ArrayList<Flight>();

        output = new StringBuilder();
        jsonStart = false;

        while((line = reader.readLine()) != null){
            statesFromPython.add(line);
        }

        System.out.println("File has been fully read, Flight(JSON) conversion starting\n");

        for(String s : statesFromPython){
            if(s.charAt(s.length()-1) == '}') {
                output.append(s);
                jsonStart = false;
                Flight flightObject = new Flight(output.toString());

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

        System.out.println("Flight's ready and returning\n");
        // JSON's ready
        return dataToUpload;
    }
}
