package org.example.trackerapi.requestBody;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetGeoReadsForAnimalIdBody {
    long animalId;
    LocalDateTime startDate;
    LocalDateTime endDate;
}
