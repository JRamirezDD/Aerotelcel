package com.reportshandler.service;

import com.reportshandler.model.AirportReport;
import com.reportshandler.repository.AirportReportRepository;
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
public class AirportReportsHandlerService {
    @Autowired
    AirportReportRepository airportReportRepository;

    public void saveReport(AirportReport airportReport) {
        airportReportRepository.save(airportReport);
    }

    public AirportReport getReportById(Long id) {
        return airportReportRepository.findById(id).orElse(null);
    }

    public List<AirportReport> getAllReports() {
        return airportReportRepository.findAll();
    }

    public void deleteReport(Long id) {
        airportReportRepository.deleteById(id);
    }

    public List<AirportReport> findReportsByIcao(String airportIcao) {
        return airportReportRepository.findByAirportIcao(airportIcao);
    }

    public long countReports() {
        return airportReportRepository.count();
    }

    public long countReportsByIcao(String airportIcao) {
        return airportReportRepository.countByAirportIcao(airportIcao);
    }
}
