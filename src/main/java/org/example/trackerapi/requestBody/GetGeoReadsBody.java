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
public class GetGeoReadsBody {
    LocalDateTime startDate;
    LocalDateTime endDate;
}
