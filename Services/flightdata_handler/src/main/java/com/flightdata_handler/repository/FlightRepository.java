package com.flightdata_handler.repository;

import com.flightdata_handler.model.Flight;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FlightRepository extends CrudRepository<Flight, Long>{
}
