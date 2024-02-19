package com.reports_handler.repository;

import com.reports_handler.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReportsRepository extends JpaRepository<Report, Long> {
    public List<Report> findByForeignId(long foreignId);
    public long countByForeignId(long foreignId);
}
