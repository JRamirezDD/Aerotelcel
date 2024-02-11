package com.flightdata_handler;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public abstract class Upload {
    Connection connToDB;

    char operation;
    String pathToFile;

    List<String> outputFromPython;
    List<JSONObject> dataToUpload;


    public Upload(Connection conn){
        this.connToDB = conn;

        this.outputFromPython = new ArrayList<String>();
        this.dataToUpload = new ArrayList<JSONObject>();

        this.pathToFile = "";
    }

    public void doSearch() throws Exception {
        this.readPython();
        this.uploadToDB();
    }

    public void readPython() throws Exception{

    }

    public void uploadToDB(){

    }

    public String getPythonPath(){
        return this.pathToFile;
    }
}
