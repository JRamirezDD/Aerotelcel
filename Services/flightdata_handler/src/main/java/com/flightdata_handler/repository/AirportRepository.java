package com.flightdata_handler.repository;

import com.flightdata_handler.model.Airport;
import feign.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


@Repository
public interface AirportRepository extends JpaRepository<Airport, String>{
    /*@Query("SELECT a FROM Airport a JOIN FETCH a.arrivals JOIN FETCH a.departures WHERE a.iata = :iata")
    Optional<Airport> findByIdWithArrivalsAndDepartures(@Param("iata") String iata);*/
}
