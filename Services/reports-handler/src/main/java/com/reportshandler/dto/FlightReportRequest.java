/*
 *    Title:  Source Code
 *    Author: Ramirez de Diego, Jorge
 *    Date: 2024
 *    Code version: 1.0
 *    Availability: https://github.com/JRamirezDD/Aerotelcel
 */

package com.reportshandler.dto;

import com.reportshandler.model.Enums.FlightReportTypeEnum;
import com.reportshandler.model.Enums.RatingEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FlightReportRequest {
    private String flightCallsign; // Replaces the Flight entity
    private FlightReportTypeEnum type;
    private RatingEnum rating;
}
