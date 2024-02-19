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
import org.springframework.stereotype.Indexed;


import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "report_id", nullable = false, updatable = false)
    private Long reportId;

    @Column(name = "foreign_id", nullable = false, updatable = false)
    private Long foreignId;
    @ManyToOne
    @JoinColumn(name = "foreign_id", nullable = false, updatable = false)
    private AviationObject aviationObject;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "rating", nullable = false, updatable = false)
    private RatingEnum rating;

    @CreatedDate
    @Column(nullable = false, insertable = false, updatable = false)
    private LocalDateTime createdDate;
}
