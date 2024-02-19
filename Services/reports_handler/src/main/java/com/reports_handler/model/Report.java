package com.reports_handler.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.flightdata_handler.model.AviationObject;
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
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({@JsonSubTypes.Type(value = AirportReport.class, name = "AirportReport"),
        @JsonSubTypes.Type(value = FlightReport.class, name = "FlightReport")
})
public abstract class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "report_id", nullable = false, updatable = false)
    private Long reportId;

    @Column(name = "foreign_id", nullable = false, updatable = false)
    private Long foreignId;

    @ManyToOne
    @JoinColumn(name = "foreign_id", nullable = false, updatable = false)
    private AviationObject aviationObject;

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
