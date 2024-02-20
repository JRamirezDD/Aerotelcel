package com.reportshandler.repository;//package com.reports_handler.repository;

import com.reportshandler.model.FlightReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FlightReportRepository extends JpaRepository<FlightReport, Long> {

    @Query("SELECT fr FROM FlightReport fr WHERE fr.flight.icao24 = :flight_icao")
    List<FlightReport> findByFlightIcao(String flight_icao);

    @Query("SELECT COUNT(fr) FROM FlightReport fr WHERE fr.flight.icao24 = :flight_icao")
    long countByFlightIcao(String flight_icao);
 }
