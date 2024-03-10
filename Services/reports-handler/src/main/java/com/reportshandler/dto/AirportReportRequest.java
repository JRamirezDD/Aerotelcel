package com.reportshandler.dto;

import com.reportshandler.model.Enums.AirportReportTypeEnum;
import com.reportshandler.model.Enums.RatingEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AirportReportRequest {
    private String airportIata; // Replaces the Airport entity
    private AirportReportTypeEnum type;
    private RatingEnum rating;
}
