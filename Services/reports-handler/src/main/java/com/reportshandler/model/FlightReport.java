/*
 *    Title:  Source Code
 *    Author: Ramirez de Diego, Jorge
 *    Date: 2024
 *    Code version: 1.0
 *    Availability: https://github.com/JRamirezDD/Aerotelcel
 */

package com.reportshandler.model;

import com.flightdata_handler.model.Flight;
import com.reportshandler.model.Enums.FlightReportTypeEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "t_FlightReports")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FlightReport extends Report {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "report_id", nullable = false, updatable = false)
    private Long reportId;

    @ManyToOne
    @JoinColumn(name = "callsign", referencedColumnName = "callsign", nullable = false, updatable = false)
    private Flight flight;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false, updatable = false)
    private FlightReportTypeEnum type;
}