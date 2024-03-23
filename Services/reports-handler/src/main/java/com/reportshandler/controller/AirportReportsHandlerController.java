/*
 *    Title: AirportReportsHandlerController Source Code
 *    Author: Ramirez de Diego, Jorge
 *    Date: 2024
 *    Code version: 1.0
 *    Availability: https://github.com/JRamirezDD/Aerotelcel
 */

package com.reportshandler.controller;

import com.reportshandler.dto.AirportReportRequest;
import com.reportshandler.dto.AirportReportResponse;
import com.reportshandler.service.AirportReportsHandlerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reports-handler/reports/airports")
@RequiredArgsConstructor
@Slf4j
public class  AirportReportsHandlerController {
    private final AirportReportsHandlerService airportReportsHandlerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveReport(@RequestBody AirportReportRequest airportReport) {
        airportReportsHandlerService.saveReport(airportReport);
    }

    // Get Report by ID - Test Regular Reports to delete
    @GetMapping("/{reportId}")
    @ResponseStatus(HttpStatus.OK)
    public AirportReportResponse getReportsById(@PathVariable Long reportId) {
        return airportReportsHandlerService.getReportById(reportId);
    }

    // Delete Report
    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void deleteReport(@RequestParam Long id) {
        airportReportsHandlerService.deleteReport(id);
    }

    // Count All Reports
    @GetMapping("/count")
    @ResponseStatus(HttpStatus.OK)
    public Long countReports() {
        return airportReportsHandlerService.countReports();
    }


    // Get All Reports
    @GetMapping
    @ResponseStatus
    public List<AirportReportResponse> getAllReports() {return airportReportsHandlerService.getAllReports(); }


    // Find Reports related to ICAO
    @GetMapping("/iata/{iata}")
    @ResponseStatus(HttpStatus.OK)
    public List<AirportReportResponse> findReportsByIata(@PathVariable String iata) {
        return airportReportsHandlerService.findReportsByIata(iata);
    }


    // Count Reports related to ICAO
    @GetMapping("/iata/{iata}/count")
    @ResponseStatus(HttpStatus.OK)
    public Long countReportsByIata(@PathVariable String iata) {
        return airportReportsHandlerService.countReportsByIata(iata);
    }

    // Query for foreignId and type?
}
