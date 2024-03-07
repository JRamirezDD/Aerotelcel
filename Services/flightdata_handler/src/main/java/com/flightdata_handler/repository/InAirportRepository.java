package com.flightdata_handler.repository;

import com.flightdata_handler.model.Airport;
import com.flightdata_handler.model.InAirport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InAirportRepository extends JpaRepository<InAirport, String> {
}
