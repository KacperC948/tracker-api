package org.example.trackerapi;

import org.example.trackerapi.model.GeoReadSummary;
import org.example.trackerapi.service.GeoReadSummaryServiceImpl;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GeoReadSummaryServiceImplTest {

    @Test
    public void testAggregateGeoReadSummaries() {
        List<GeoReadSummary> summaries = Arrays.asList(
                new GeoReadSummary(1, 1, LocalDate.of(2023, 1, 1), 10.0, 20.0, 1, 100, "TypeA"),
                new GeoReadSummary(2, 1, LocalDate.of(2023, 1, 1), 15.0, 25.0, 2, 200, "TypeA"),
                new GeoReadSummary(3, 1, LocalDate.of(2023, 1, 2), 20.0, 30.0, 3, 300, "TypeB")
        );

        List<GeoReadSummary> result = GeoReadSummaryServiceImpl.aggregateGeoReadSummaries(summaries);

        assertEquals(2, result.size());

        GeoReadSummary summary1 = result.get(1);
        assertEquals(LocalDate.of(2023, 1, 1), summary1.getDate());
        assertEquals("TypeA", summary1.getAnimalType());
        assertEquals(25.0, summary1.getDistance());
        assertEquals(22.5, summary1.getTempAvg());
        assertEquals(3, summary1.getOutOfShepherdCounter());
        assertEquals(300, summary1.getIdleTime());

        GeoReadSummary summary2 = result.getFirst();
        assertEquals(LocalDate.of(2023, 1, 2), summary2.getDate());
        assertEquals("TypeB", summary2.getAnimalType());
        assertEquals(20.0, summary2.getDistance());
        assertEquals(30.0, summary2.getTempAvg());
        assertEquals(3, summary2.getOutOfShepherdCounter());
        assertEquals(300, summary2.getIdleTime());
    }
}
