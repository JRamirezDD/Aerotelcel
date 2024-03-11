package com.flightdata_handler.repository;

import com.flightdata_handler.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface FlightRepository extends JpaRepository<Flight, String> {

    @Query("SELECT f FROM Flight f WHERE f.callsign = :flightCode")
    public Flight findByFlightCode(String flightCode);
}
