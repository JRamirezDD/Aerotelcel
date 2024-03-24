/*
 *    Title: Source Code
 *    Author: Ortega Mendoza, Javier
 *    Date: 2024
 *    Code version: 1.0
 *    Availability: https://github.com/JRamirezDD/Aerotelcel
 */

package com.flightdata_handler.repository;

import com.flightdata_handler.model.Airport;
import feign.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


@Repository
public interface AirportRepository extends JpaRepository<Airport, String>{
    @Query("SELECT a FROM Airport a WHERE a.icao = :icao")
    public Optional<Airport> findByIcao(@Param("icao") String icao);
}
