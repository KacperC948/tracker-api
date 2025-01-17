package org.example.trackerapi;


import org.example.trackerapi.model.GeoRead;
import org.example.trackerapi.service.GeoReadServiceImpl;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GeoReadServiceImplTest {

    @Test
    public void testCalculateIdleTime() {
        GeoReadServiceImpl geoReadService = new GeoReadServiceImpl();

        GeoRead geoRead1 = new GeoRead();
        geoRead1.setLatitude(50.0);
        geoRead1.setLongitude(20.0);
        geoRead1.setCreatedDate(LocalDateTime.of(2023, 10, 1, 10, 0));

        GeoRead geoRead2 = new GeoRead();
        geoRead2.setLatitude(50.0);
        geoRead2.setLongitude(20.0);
        geoRead2.setCreatedDate(LocalDateTime.of(2023, 10, 1, 12, 0));

        GeoRead geoRead3 = new GeoRead();
        geoRead3.setLatitude(51.0);
        geoRead3.setLongitude(21.0);
        geoRead3.setCreatedDate(LocalDateTime.of(2023, 10, 1, 14, 0));

        List<GeoRead> geoReads = Arrays.asList(geoRead1, geoRead2, geoRead3);

        long idleTime = geoReadService.calculateIdleTime(geoReads);

        assertEquals(7200, idleTime); // 2 hours in seconds
    }
}
