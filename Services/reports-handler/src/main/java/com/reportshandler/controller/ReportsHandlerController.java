package com.reportshandler.controller;

import com.reportshandler.model.Report;
import com.reportshandler.service.ReportsHandlerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reports-handler/reports")
@RequiredArgsConstructor
@Slf4j
public class ReportsHandlerController {
    private final ReportsHandlerService reportsHandlerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveReport(@RequestBody Report report) {
        reportsHandlerService.saveReport(report);
    }

    @GetMapping("{reportId}")
    @ResponseStatus(HttpStatus.OK)
    public Report getReportsById(@PathVariable Long reportId) {
            return reportsHandlerService.getReportById(reportId);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void deleteReport(@RequestParam Long id) {
        reportsHandlerService.deleteReport(id);
    }

    @GetMapping("/count")
    @ResponseStatus(HttpStatus.OK)
    public Long countReports() {
        return reportsHandlerService.countReports();
    }

    @GetMapping
    @ResponseStatus
    public List<Report> getAllReports() {return reportsHandlerService.getAllReports(); }

//    @GetMapping("/countById")
//    @ResponseStatus(HttpStatus.OK)
//    public Long countReportsById(@RequestParam Long foreignId) {
//        return reportsHandlerService.countReportsById(foreignId);
//    }

    // Query for foreignId and type?
}
