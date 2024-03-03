package com.reportshandler.repository;//package com.reports_handler.repository;

import com.reportshandler.model.AirportReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AirportReportRepository extends JpaRepository<AirportReport, Long> {
    @Query("SELECT ar FROM AirportReport ar WHERE ar.airport.ICAO_code = :airport_icao")
    public List<AirportReport> findByAirportIcao(String airport_icao);

    @Query("SELECT COUNT(ar) FROM AirportReport ar WHERE ar.airport.ICAO_code = :airport_icao")
    public long countByAirportIcao(String airport_icao);
}