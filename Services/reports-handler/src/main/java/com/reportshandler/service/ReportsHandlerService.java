package com.reportshandler.service;

import com.reportshandler.model.Report;
import com.reportshandler.repository.ReportsRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ReportsHandlerService {
    @Autowired
    ReportsRepository reportsRepository;

    public void saveReport(Report report) {
        reportsRepository.save(report);
    }

    public Report getReportById(Long id) {
        return reportsRepository.findById(id).orElse(null);
    }

    public List<Report> getAllReports() {
        return reportsRepository.findAll();
    }

    public void deleteReport(Long id) {
        reportsRepository.deleteById(id);
    }

//    public List<Report> findReportsByForeignId(Long foreignId) {
//        return reportsRepository.findByForeignId(foreignId);
//    }

    public long countReports() {
        return reportsRepository.count();
    }

//    public long countReportsById(long foreignId) {
//        return reportsRepository.countByForeignId(foreignId);
//    }
}
