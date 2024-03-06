package com.reportshandler.controller;

import com.reportshandler.model.FlightReport;
import com.reportshandler.model.Report;
import com.reportshandler.service.FlightReportsHandlerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reports-handler/reports/flights")
@RequiredArgsConstructor
@Slf4j
public class FlightReportsHandlerController {
    private final FlightReportsHandlerService flightReportsHandlerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveReport(@RequestBody FlightReport flightReport) {
        flightReportsHandlerService.saveReport(flightReport);
    }

    // Get Report by ID - Test Regular Reports to delete
    @GetMapping("{reportId}")
    @ResponseStatus(HttpStatus.OK)
    public Report getReportsById(@PathVariable Long reportId) {
        return flightReportsHandlerService.getReportById(reportId);
    }

    // Delete Report
    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void deleteReport(@RequestParam Long id) {
        flightReportsHandlerService.deleteReport(id);
    }

    // Count All Reports
    @GetMapping("/count")
    @ResponseStatus(HttpStatus.OK)
    public Long countReports() {
        return flightReportsHandlerService.countReports();
    }


    // Get All Reports
    @GetMapping
    @ResponseStatus
    public List<FlightReport> getAllReports() {return flightReportsHandlerService.getAllReports(); }


    // Find Reports related to ICAO
    @GetMapping("/icao/{Icao}")
    @ResponseStatus(HttpStatus.OK)
    public List<FlightReport> findReportsByIcao(@RequestParam String Icao) {
        return flightReportsHandlerService.findReportsByCallsign(Icao);
    }


    // Count Reports related to ICAO
    @GetMapping("/icao/count")
    @ResponseStatus(HttpStatus.OK)
    public Long countReportsByIcao(@RequestParam String Icao) {
        return flightReportsHandlerService.countReportsByCallsign(Icao);
    }

    // Query for foreignId and type?
}
