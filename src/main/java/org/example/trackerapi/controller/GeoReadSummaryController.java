package org.example.trackerapi.controller;

import lombok.RequiredArgsConstructor;
import org.example.trackerapi.model.GeoReadSummary;
import org.example.trackerapi.requestBody.GetSummaryBody;
import org.example.trackerapi.requestBody.GetSummaryByAnimalIdBody;
import org.example.trackerapi.requestBody.GetSummaryByTypeBody;
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

    @PostMapping("/getSummary")
    public List<GeoReadSummary> getSummary(@RequestBody GetSummaryBody getSummaryBody) {
        return geoReadSummaryService.getByDateRange(getSummaryBody.getStartDate(),
                getSummaryBody.getEndDate());
    }

    @PostMapping("/getSummaryByAnimalType")
    public List<GeoReadSummary> getSummaryByType(@RequestBody GetSummaryByTypeBody getSummaryByTypeBody) {
        return geoReadSummaryService.getByDateRangeAndAnimalType(getSummaryByTypeBody.getStartDate(),
                getSummaryByTypeBody.getEndDate(), getSummaryByTypeBody.getAnimalType());
    }

    @PostMapping("/getSummaryByAnimalId")
    public List<GeoReadSummary> getSummaryByAnimalId(@RequestBody GetSummaryByAnimalIdBody getSummaryByAnimalIdBody) {
        return geoReadSummaryService.getByDateRangeAndAnimalId(getSummaryByAnimalIdBody.getStartDate(),
                getSummaryByAnimalIdBody.getEndDate(), getSummaryByAnimalIdBody.getAnimalId());
    }

}
