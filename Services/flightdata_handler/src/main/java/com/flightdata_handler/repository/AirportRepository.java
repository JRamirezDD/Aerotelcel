package com.flightdata_handler.repository;

import com.flightdata_handler.model.Airport;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface AirportRepository extends JpaRepository<Airport, String>{
}
