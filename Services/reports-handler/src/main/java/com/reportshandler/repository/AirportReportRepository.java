/*
 *    Title:  Source Code
 *    Author: Ramirez de Diego, Jorge
 *    Date: 2024
 *    Code version: 1.0
 *    Availability: https://github.com/JRamirezDD/Aerotelcel
 */

package com.reportshandler.repository;//package com.reports_handler.repository;

import com.reportshandler.model.AirportReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AirportReportRepository extends JpaRepository<AirportReport, Long> {
    @Query("SELECT ar FROM AirportReport ar WHERE ar.airport.iata = :airport_iata")
    public List<AirportReport> findByAirportIata(String airport_iata);

    @Query("SELECT COUNT(ar) FROM AirportReport ar WHERE ar.airport.iata = :airport_iata")
    public long countByAirportIata(String airport_iata);
}
