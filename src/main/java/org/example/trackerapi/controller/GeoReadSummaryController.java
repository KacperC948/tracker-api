package org.example.trackerapi.controller;

import lombok.RequiredArgsConstructor;
import org.example.trackerapi.model.GeoReadSummary;
import org.example.trackerapi.requestBody.GetSummaryBody;
import org.example.trackerapi.service.GeoReadSummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/geoReadSummary")
@RequiredArgsConstructor
public class GeoReadSummaryController {

    @Autowired
    private GeoReadSummaryService geoReadSummaryService;

    @GetMapping("/getSummary")
    public List<GeoReadSummary> getSummary(@RequestBody GetSummaryBody getSummaryBody) {
        return geoReadSummaryService.getByDateRange(getSummaryBody.getStartDate(),
                getSummaryBody.getEndDate());
    }

}
