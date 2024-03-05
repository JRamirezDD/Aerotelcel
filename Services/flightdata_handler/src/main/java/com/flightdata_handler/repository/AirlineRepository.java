package com.flightdata_handler.repository;

import com.flightdata_handler.model.Airline;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface AirlineRepository extends JpaRepository<Airline, String>{
}
