package org.example.trackerapi.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
//@Getter
//@Setter
public class GeoReadDto {
    private long animalId;
    private long trackerId;
    private LocalDateTime createdDate;
    private double longitude;
    private double latitude;
    private double currentTemp;
    private boolean isTempExceeded;
    private boolean isAnimalInShepherd;
    private boolean isTempExceededConfirmed;
    private boolean isAnimalInShepherdConfirmed;
}
