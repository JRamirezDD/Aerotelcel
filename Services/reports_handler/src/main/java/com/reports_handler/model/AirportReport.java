package com.reports_handler.model;

import com.flightdata_handler.model.Airport;
import com.flightdata_handler.model.AviationObject;
import com.flightdata_handler.model.Flight;
import com.reports_handler.model.Enums.AirportReportTypeEnum;
import com.reports_handler.model.Enums.RatingEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "t_AirportReports")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AirportReport {
//    @ManyToOne
//    @JoinColumn(name = "foreign_id", referencedColumnName = "id")
//    private Airport airport;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "report_id", nullable = false, updatable = false)
    private Long reportId;

    @ManyToOne
    @JoinColumn(name = "airport_id", referencedColumnName = "id", nullable = false, updatable = false)
    private Airport airport;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false, updatable = false)
    private AirportReportTypeEnum type;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "rating", nullable = false, updatable = false)
    private RatingEnum rating;

    @CreatedDate
    @Column(nullable = false, insertable = false, updatable = false)
    private LocalDateTime createdDate;
}