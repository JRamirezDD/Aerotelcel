package com.flightdata_handler.repository;

import com.flightdata_handler.model.Airport;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AirportRepository extends CrudRepository<Airport, String>{
}
