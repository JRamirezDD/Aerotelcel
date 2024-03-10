package com.flightdata_handler.repository;

import com.flightdata_handler.model.Arrivals;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArrivalsRepository extends JpaRepository<Arrivals, String> {
}
