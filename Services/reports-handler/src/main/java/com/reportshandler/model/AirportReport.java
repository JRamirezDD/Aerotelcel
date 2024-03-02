package com.reportshandler.model;

import com.flightdata_handler.model.Airport;
import com.reportshandler.model.Enums.AirportReportTypeEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "t_AirportReports")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AirportReport extends Report {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "report_id", nullable = false, updatable = false)
    private Long reportId;

    @ManyToOne
    @JoinColumn(name = "airport_icao", referencedColumnName = "ICAO_code", nullable = false, updatable = false)
    private Airport airport;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false, updatable = false)
    private AirportReportTypeEnum type;
}