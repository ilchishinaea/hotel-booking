package com.example.hotelbooking.statistics.controller;

import com.example.hotelbooking.statistics.service.StatisticsCsvService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/statistics")
@RequiredArgsConstructor
public class StatisticsController {

    private static final String FILE_NAME = "statistics.csv";

    private final StatisticsCsvService statisticsCsvService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/csv")
    public ResponseEntity<byte[]> downloadCsv() {
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("text/csv"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + FILE_NAME + "\"")
                .body(statisticsCsvService.exportToCsv());
    }
}
