package com.reportshandler.repository;//package com.reports_handler.repository;

import com.reportshandler.model.FlightReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FlightReportRepository extends JpaRepository<FlightReport, Long> {

    @Query("SELECT fr FROM FlightReport fr WHERE fr.flight.callsign = :flight_callsign")
    List<FlightReport> findByFlightCallsign(String flight_callsign);

    @Query("SELECT COUNT(fr) FROM FlightReport fr WHERE fr.flight.callsign = :flight_callsign")
    long countByFlightCallsign(String flight_callsign);
 }
