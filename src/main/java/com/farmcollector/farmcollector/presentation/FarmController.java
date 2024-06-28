package com.farmcollector.farmcollector.presentation;

import com.farmcollector.farmcollector.core.dto.HarvestRequest;
import com.farmcollector.farmcollector.core.dto.PlantRequest;
import com.farmcollector.farmcollector.core.service.FarmService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/farm")
public class FarmController {

    private final FarmService farmService;

    @Autowired
    public FarmController(FarmService farmService) {
        this.farmService = farmService;
    }

    /**
     * Saves planted data for a farm and crop type for a specific season.
     * @return true if saving is successful, false otherwise.
     */
    @PostMapping("/plant")
    public ResponseEntity<Boolean> plant(@RequestBody PlantRequest request) {
        return ResponseEntity.ok(farmService.plant(request));
    }

    /**
     * Updates harvesting data for an existing planted data
     * @param request
     * @return
     */
    @PutMapping("/harvest")
    public ResponseEntity<Boolean> harvest(@RequestBody HarvestRequest request) {
        return ResponseEntity.ok(farmService.harvest(request));
    }
}
