package com.farmcollector.farmcollector.core.service;

import com.farmcollector.farmcollector.core.dto.HarvestRequest;
import com.farmcollector.farmcollector.core.dto.PlantRequest;
import com.farmcollector.farmcollector.core.dto.Season;
import com.farmcollector.farmcollector.core.entity.Farm;

import java.util.List;

public interface FarmService {

    Boolean plant(PlantRequest request);

    Boolean harvest(HarvestRequest request);

    List<Farm> farmBySeason(Season season);
}
