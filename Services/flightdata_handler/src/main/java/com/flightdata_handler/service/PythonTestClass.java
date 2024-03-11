package com.flightdata_handler.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Service
@Slf4j
public class PythonTestClass {

    @Value("${python.file.pythonTest}")
    private String pathToPythonTest;

    public void testPython() {
        log.info("Python test starting...");

        log.info("Path to python test: " + pathToPythonTest + "\n");

        try {
            ProcessBuilder processBuilder = new ProcessBuilder("python", pathToPythonTest);
            Process process = processBuilder.start();
            process.waitFor();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            log.info("Python test finished, information read: \n");
            log.info("Output: " + reader.readLine() + "\n");

        } catch (Exception e) {
            log.error("Error running python test: " + e);
        }
    }
}
