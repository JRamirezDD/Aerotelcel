/*
 *    Title:  Source Code
 *    Author: Ramirez de Diego, Jorge
 *    Date: 2024
 *    Code version: 1.0
 *    Availability: https://github.com/JRamirezDD/Aerotelcel
 */

package com.reportshandler.service;

import com.reportshandler.dto.FlightReportRequest;
import com.reportshandler.dto.FlightReportResponse;
import com.reportshandler.model.FlightReport;
import com.reportshandler.repository.FlightReportRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class FlightReportsHandlerService {
    FlightReportRepository flightReportRepository;

    public void saveReport(FlightReportRequest flightReport) {
        flightReportRepository.save(requestToReport(flightReport));
    }

    public FlightReportResponse getReportById(Long id) {
        FlightReport flightReport = flightReportRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No Flight Report found with ID: " + id));

        return reportToResponse(flightReport);
    }

    public List<FlightReportResponse> getAllReports() {
        return flightReportRepository.findAll().stream()
                .map(this::reportToResponse)
                .collect(Collectors.toList());
    }

    public void deleteReport(Long id) {
        flightReportRepository.deleteById(id);
    }

    public List<FlightReportResponse> findReportsByCallsign(String flightIcao) {
        return flightReportRepository.findByFlightCallsign(flightIcao).stream()
                .map(this::reportToResponse)
                .collect(Collectors.toList());
    }

    public long countReports() {
        return flightReportRepository.count();
    }

    public long countReportsByCallsign(String flight_callsign) {
        return flightReportRepository.countByFlightCallsign(flight_callsign);
    }

    private FlightReportResponse reportToResponse(FlightReport flightReport) {
        return new FlightReportResponse(
                flightReport.getFlight().getCallsign(),
                flightReport.getType(),
                flightReport.getRating()
        );
    }

    private FlightReport requestToReport(FlightReportRequest flightReportRequest) {
        // Assuming you have a method or service to fetch the Flight entity by callsign
        return null;
    }
}
