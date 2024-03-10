package com.reportshandler.service;

import com.reportshandler.dto.AirportReportRequest;
import com.reportshandler.dto.AirportReportResponse;
import com.reportshandler.model.AirportReport;
import com.reportshandler.model.FlightReport;
import com.reportshandler.repository.AirportReportRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class AirportReportsHandlerService {
    @Autowired
    AirportReportRepository airportReportRepository;

    // Temporarily Unavailable
    public void saveReport(AirportReportRequest airportReportRequest) {
        airportReportRepository.save(requestToReport(airportReportRequest));
    }

    public AirportReportResponse getReportById(Long id) {
        AirportReport flightReport = airportReportRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No Flight Report found with ID: " + id));

        return reportToResponse(flightReport);
    }

    public List<AirportReportResponse> getAllReports() {
        return airportReportRepository.findAll().stream()
                .map(this::reportToResponse)
                .collect(Collectors.toList());
    }

    public void deleteReport(Long id) {
        airportReportRepository.deleteById(id);
    }

    public List<AirportReportResponse> findReportsByIata(String airport_iata) {
        return airportReportRepository.findByAirportIata(airport_iata).stream()
                .map(this::reportToResponse)
                .collect(Collectors.toList());
    }

    public long countReports() {
        return airportReportRepository.count();
    }

    public long countReportsByIata(String airport_iata) {
        return airportReportRepository.countByAirportIata(airport_iata);
    }

    private AirportReportResponse reportToResponse(AirportReport airportReport) {
        return new AirportReportResponse(
                airportReport.getAirport().getIata(),
                airportReport.getType(),
                airportReport.getRating()
        );
    }

    private AirportReport requestToReport(AirportReportRequest airportReportRequest) {
        // Assuming you have a method or service to fetch the Airport entity by Iata
        return null;
    }
}
