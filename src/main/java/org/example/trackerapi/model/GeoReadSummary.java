package org.example.trackerapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.trackerapi.utils.LocalDateAttributeConverter;

import java.time.Duration;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "geo_read_summaries")
public class GeoReadSummary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "animal_id")
    private long animalId;

    @Column(name = "date")
    @Convert(converter = LocalDateAttributeConverter.class)
    private LocalDate date;

    @Column(name = "distance")
    private double distance;

    @Column(name = "temp_avg")
    private double tempAvg;

    @Column(name = "out_of_shepherd_counter")
    private int outOfShepherdCounter;

    @Column(name = "idle_time")
    private Duration idleTime;

    @Column(name = "animal_type")
    private String animalType;
}
