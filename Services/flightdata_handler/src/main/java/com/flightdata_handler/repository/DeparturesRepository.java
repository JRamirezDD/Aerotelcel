package com.flightdata_handler.repository;

import com.flightdata_handler.model.Departures;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeparturesRepository extends JpaRepository<Departures, String>  {
}