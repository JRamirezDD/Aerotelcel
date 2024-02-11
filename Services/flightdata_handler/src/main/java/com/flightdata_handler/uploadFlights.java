package com.flightdata_handler;

import org.json.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class uploadFlights extends Upload {

    // Variables to read file
    Process process;
    BufferedReader reader;
    String line;

    private List<String> statesFromPython;

    // Variables to turn data into JSON
    StringBuilder output;
    boolean jsonStart;
    private List<JSONObject> dataToUpload;
    
    // Variables to upload to DB
    ProcessBuilder processBuilder;

    // Other variables


    public uploadFlights(Connection conn){
        super(conn);

        // Replace with actual path
        this.pathToFile = "C:\\Users\\javie\\Documents\\Code_Man_Laptop\\Aerotelcel\\Services\\flightdata_handler\\src\\main\\python\\getAllFlights.py";
    }

    // Returns all read flights as a JSON list
    public void readPython() throws Exception {
        processBuilder = new ProcessBuilder("python", this.pathToFile);
        process = processBuilder.start();

        reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

        statesFromPython = new ArrayList<String>();
        dataToUpload = new ArrayList<JSONObject>();

        output = new StringBuilder();
        jsonStart = false;

        while((line = reader.readLine()) != null){
            statesFromPython.add(line);
        }

        System.out.println("File has been fully read, JSON conversion starting\n");

        for(String s : statesFromPython){
            if(s.charAt(s.length()-1) == '}') {
                output.append(s);
                jsonStart = false;
                JSONObject jsonObject = new JSONObject(output.toString());

                dataToUpload.add(jsonObject);
                output = new StringBuilder();
            } else if (jsonStart) {
                output.append(s);
            }

            if (s.contains("{") && !jsonStart) {
                jsonStart = true;
                output.append(s);
            }
        }

        System.out.println("All data read: " + dataToUpload.size() + " flights.\n");
    }

    // Uploads all data to the DB
    public void uploadToDB(){
        try {
            System.out.println("Uploading data to DB");
            for (JSONObject s : dataToUpload) {
                // Turn time_position and last_contact into the right date format to DB
                int time_position = s.optInt("time_position", 1);
                Timestamp timePositionTimestamp = time_position > 0 ? new Timestamp((time_position * 1000L)) : null;

                int last_contact = s.optInt("last_contact", 1);
                Timestamp lastContactTimestamp = last_contact > 0 ? new Timestamp((last_contact * 1000L)) : null;

                // Prepared Statement to insert into DB
                String sql = "INSERT INTO flights (icao24, callsign, origin_country, time_position, last_contact, longitude, latitude, baro_altitude, velocity, true_track, vertical_rate, sensors, geo_altitude, squawk, position_source, category) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement prepSt = connToDB.prepareStatement(sql);

                // Set values for prepared statement
                prepSt.setString(1, s.optString("icao24", null));
                prepSt.setString(2, s.optString("callsign", null));
                prepSt.setString(3, s.optString("origin_country", null));
                prepSt.setTimestamp(4, (timePositionTimestamp != null ? timePositionTimestamp : null));
                prepSt.setTimestamp(5, (lastContactTimestamp != null ? lastContactTimestamp : null));
                prepSt.setFloat(6, s.optFloat("longitude", 0));
                prepSt.setFloat(7, s.optFloat("latitude", 0));
                prepSt.setFloat(8, s.optFloat("baro_altitude", 0));
                prepSt.setFloat(9, s.optFloat("velocity", 0));
                prepSt.setFloat(10, s.optFloat("true_track", 0));
                prepSt.setFloat(11, s.optFloat("vertical_rate", 0));
                prepSt.setString(12, s.optString("sensors", null));
                prepSt.setFloat(13, s.optFloat("geo_altitude", 0));
                prepSt.setString(14, s.optString("squawk", null));
                prepSt.setInt(15, s.optInt("position_source", 0));
                prepSt.setInt(16, s.optInt("category", 0));

                // Execute prepared statement
                prepSt.executeUpdate();
            }

            System.out.println("All flights inserted into DB\n");

        } catch (Exception e) {

            System.out.println("Error: " + e + ", Continuing rn\n");
        } 
    }
}
