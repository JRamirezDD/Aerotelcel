/*
 *    Title:  Source Code
 *    Author: Ramirez de Diego, Jorge
 *    Date: 2024
 *    Code version: 1.0
 *    Availability: https://github.com/JRamirezDD/Aerotelcel
 */

package com.reportshandler.dto;

import com.reportshandler.model.Enums.AirportReportTypeEnum;
import com.reportshandler.model.Enums.RatingEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AirportReportResponse {
    private String airportIata; // Replaces the Airport entity
    private AirportReportTypeEnum type;
    private RatingEnum rating;
}
