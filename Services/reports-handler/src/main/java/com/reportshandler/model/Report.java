package com.reportshandler.model;

import com.reportshandler.model.Enums.RatingEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@MappedSuperclass
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Report {
    @Enumerated(EnumType.STRING)
    @Column(name = "rating", nullable = false, updatable = false)
    private RatingEnum rating;
}
