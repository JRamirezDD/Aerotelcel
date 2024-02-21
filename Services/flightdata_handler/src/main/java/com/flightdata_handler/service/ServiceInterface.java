package com.flightdata_handler.service;

import java.io.IOException;

public interface ServiceInterface {
    public void doSearch() throws Exception;

    default boolean readPython() throws IOException{
        return false;
    };

    public String getPythonPath();
}
