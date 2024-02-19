package com.reports_handler.controller;

import com.reports_handler.model.Report;
import com.reports_handler.service.ReportsHandlerService;
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

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Report getReportsById(@RequestParam(required = false) Long Id) {
        return reportsHandlerService.getReportById(Id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Report> getReportsByForeignId(@RequestParam(required = false) Long foreignId) {
        return reportsHandlerService.findReportsByForeignId(foreignId);
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

    @GetMapping("/countById")
    @ResponseStatus(HttpStatus.OK)
    public Long countReportsById(@RequestParam Long foreignId) {
        return reportsHandlerService.countReportsById(foreignId);
    }

    // Query for foreignId and type?
}
