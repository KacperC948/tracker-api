package org.example.trackerapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnimalDto {
    private String name;
    private double tempMax;
    private double tempMin;
    private String type;
}
