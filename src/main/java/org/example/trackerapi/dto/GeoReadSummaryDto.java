package org.example.trackerapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GeoReadSummaryDto {
    private long id;
    private AnimalDto animal;
    private String date;
    private double distance;
    private double tempAvg;
    private int outOfShepherdCounter;
    private Duration idleTime;
}
