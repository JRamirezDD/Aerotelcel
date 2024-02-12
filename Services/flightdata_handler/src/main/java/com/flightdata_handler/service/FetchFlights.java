package com.flightdata_handler.service;

import com.flightdata_handler.model.Flight;

import java.io.IOException;
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

    public FetchFlights(){
        this.outputFromPython = new ArrayList<String>();
        this.dataToUpload = new ArrayList<Flight>();

        this.pathToFile = "";
    }

    public void doSearch() throws Exception {
        this.readPython();
        this.uploadToDB();
    }

    public void readPython() throws IOException {
    }

    public void uploadToDB(){

    }

    public String getPythonPath(){
        return this.pathToFile;
    }
}
