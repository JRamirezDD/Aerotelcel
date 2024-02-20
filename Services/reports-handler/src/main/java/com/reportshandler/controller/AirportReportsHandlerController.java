package com.reportshandler.controller;

import com.reportshandler.model.AirportReport;
import com.reportshandler.model.Report;
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
public class AirportReportsHandlerController {
    // Get All Reports
    // Get Reports related to AirportID
    // Get Report by ID
    // Add Airport Report
    // Modify Airport Report
        // Set Airport Report?

    private final AirportReportsHandlerService airportReportsHandlerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveReport(@RequestBody AirportReport airportReport) {
        airportReportsHandlerService.saveReport(airportReport);
    }

    // Get Report by ID - Test Regular Reports to delete
    @GetMapping("{reportId}")
    @ResponseStatus(HttpStatus.OK)
    public Report getReportsById(@PathVariable Long reportId) {
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
    public List<AirportReport> getAllReports() {return airportReportsHandlerService.getAllReports(); }


    // Find Reports related to ICAO
    @GetMapping("/icao/{Icao}")
    @ResponseStatus(HttpStatus.OK)
    public List<AirportReport> findReportsByIcao(@RequestParam String Icao) {
        return airportReportsHandlerService.findReportsByIcao(Icao);
    }


    // Count Reports related to ICAO
    @GetMapping("/icao/count")
    @ResponseStatus(HttpStatus.OK)
    public Long countReportsByIcao(@RequestParam String Icao) {
        return airportReportsHandlerService.countReportsByIcao(Icao);
    }

    // Query for foreignId and type?
}
