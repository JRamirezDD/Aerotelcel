package com.reportshandler.controller;

import com.reportshandler.dto.FlightReportRequest;
import com.reportshandler.dto.FlightReportResponse;
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
    public void saveReport(@RequestBody FlightReportRequest flightReport) {
        flightReportsHandlerService.saveReport(flightReport);
    }

    // Get Report by ID - Test Regular Reports to delete
    @GetMapping("{reportId}")
    @ResponseStatus(HttpStatus.OK)
    public FlightReportResponse getReportsById(@PathVariable Long reportId) {
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
    public List<FlightReportResponse> getAllReports() {return flightReportsHandlerService.getAllReports(); }


    // Find Reports related to ICAO
    @GetMapping("/callsign/{callsign}")
    @ResponseStatus(HttpStatus.OK)
    public List<FlightReportResponse> findReportsByCallsign(@PathVariable String callsign) {
        return flightReportsHandlerService.findReportsByCallsign(callsign);
    }


    // Count Reports related to ICAO
    @GetMapping("/callsign/{callsign}/count")
    @ResponseStatus(HttpStatus.OK)
    public Long countReportsByCallsign(@PathVariable String callsign) {
        return flightReportsHandlerService.countReportsByCallsign(callsign);
    }

    // Query for foreignId and type?
}
