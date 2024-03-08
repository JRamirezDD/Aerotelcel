package com.flightdata_handler.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "openskytestdb_airlinesdb")
public class Airline {

    @Column(name = "IATA")
    @JsonProperty("IATA")
    private String iata;

    @Id
    @Column(name = "ICAO")
    @JsonProperty("ICAO")
    private String icao;

    @Column(name = "Airline")
    @JsonProperty("Airline")
    private String airline;
}
