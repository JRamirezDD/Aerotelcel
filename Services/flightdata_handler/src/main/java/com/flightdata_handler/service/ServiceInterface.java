package com.flightdata_handler.service;

import com.flightdata_handler.model.Flight;

import java.io.IOException;
import java.util.List;

public interface ServiceInterface {
    public void doSearch() throws Exception;

    default boolean readPython() throws IOException{
        return false;
    };

    public String getPythonPath();
}
