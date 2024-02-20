//package com.reportshandler.model;
//
//import com.reports_handler.model.Enums.FlightReportTypeEnum;
//import com.reports_handler.model.Enums.RatingEnum;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import org.springframework.data.annotation.CreatedDate;
//
//import javax.persistence.*;
//import java.time.LocalDateTime;
//
//@Entity
//@Table(name = "t_AirportReports")
//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//public class FlightReport {
////    @ManyToOne
////    @JoinColumn(name = "foreign_id", referencedColumnName = "id")
////    private Flight flight;
//
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(name = "report_id", nullable = false, updatable = false)
//    private Long reportId;
//
////    @ManyToOne
////    @JoinColumn(name = "flight_id", referencedColumnName = "id", nullable = false, updatable = false)
////    private Flight flight;
//
//    @Enumerated(EnumType.STRING)
//    @Column(name = "type", nullable = false, updatable = false)
//    private FlightReportTypeEnum type;
//
//    @Enumerated(EnumType.ORDINAL)
//    @Column(name = "rating", nullable = false, updatable = false)
//    private RatingEnum rating;
//
//    @CreatedDate
//    @Column(nullable = false, insertable = false, updatable = false)
//    private LocalDateTime createdDate;
//}