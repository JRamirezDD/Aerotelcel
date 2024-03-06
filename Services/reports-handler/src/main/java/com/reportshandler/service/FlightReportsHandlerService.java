package com.reportshandler.service;

import com.reportshandler.model.FlightReport;
import com.reportshandler.repository.FlightReportRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class FlightReportsHandlerService {
    @Autowired
    FlightReportRepository flightReportRepository;

    public void saveReport(FlightReport flightReport) {
        flightReportRepository.save(flightReport);
    }

    public FlightReport getReportById(Long id) {
        return flightReportRepository.findById(id).orElse(null);
    }

    public List<FlightReport> getAllReports() {
        return flightReportRepository.findAll();
    }

    public void deleteReport(Long id) {
        flightReportRepository.deleteById(id);
    }

    public List<FlightReport> findReportsByCallsign(String flightIcao) {
        return flightReportRepository.findByFlightCallsign(flightIcao);
    }

    public long countReports() {
        return flightReportRepository.count();
    }

    public long countReportsByCallsign(String flight_callsign) {
        return flightReportRepository.countByFlightCallsign(flight_callsign);
    }
}
