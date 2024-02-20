package com.reportshandler.repository;

import com.reportshandler.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportsRepository extends JpaRepository<Report, Long> {
//    public List<Report> findByForeignId(long foreignId);
//    public long countByForeignId(long foreignId);
}
