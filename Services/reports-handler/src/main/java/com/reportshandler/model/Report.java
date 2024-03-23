/*
 *    Title:  Source Code
 *    Author: Ramirez de Diego, Jorge
 *    Date: 2024
 *    Code version: 1.0
 *    Availability: https://github.com/JRamirezDD/Aerotelcel
 */

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
