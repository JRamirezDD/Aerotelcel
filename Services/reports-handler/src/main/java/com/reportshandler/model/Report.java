package com.reportshandler.model;

import com.reportshandler.model.Enums.RatingEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;


import java.time.LocalDateTime;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
//@Table(name = "t_reports")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "report_id", nullable = false, updatable = false)
    private Long reportId;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "rating", nullable = false, updatable = false)
    private RatingEnum rating;
}
