package org.example.trackerapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnimalShepherdDto {
    private String animalType;
    private double longitude;
    private double latitude;
}
