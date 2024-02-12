package com.flightdata_handler.service;

import com.flightdata_handler.model.Flight;
import org.json.JSONObject;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public abstract class FetchFlights {
    Connection connToDB;

    char operation;
    String pathToFile;

    List<String> outputFromPython;
    List<Flight> dataToUpload;


    public FetchFlights(Connection conn){
        this.connToDB = conn;

        this.outputFromPython = new ArrayList<String>();
        this.dataToUpload = new ArrayList<Flight>();

        this.pathToFile = "";
    }

    public void doSearch() throws Exception {
        this.readPython();
        this.uploadToDB();
    }

    public List<Flight> readPython() throws Exception{
        return dataToUpload;
    }

    public void uploadToDB(){

    }

    public String getPythonPath(){
        return this.pathToFile;
    }
}
