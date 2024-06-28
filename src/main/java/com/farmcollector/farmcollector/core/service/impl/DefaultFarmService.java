package com.farmcollector.farmcollector.core.service.impl;

import com.farmcollector.farmcollector.core.dto.HarvestRequest;
import com.farmcollector.farmcollector.core.dto.PlantRequest;
import com.farmcollector.farmcollector.core.dto.Season;
import com.farmcollector.farmcollector.core.entity.Farm;
import com.farmcollector.farmcollector.core.repository.FarmRepository;
import com.farmcollector.farmcollector.core.service.FarmService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class DefaultFarmService implements FarmService {

    private final FarmRepository farmRepository;

    @Autowired
    public DefaultFarmService(FarmRepository farmRepository) {
        this.farmRepository = farmRepository;
    }

    @Override
    public Boolean plant(PlantRequest request) {
        try {
            farmRepository.save(Farm.builder()
                            .name(request.farmName())
                            .crop(request.cropType())
                            .season(request.season())
                            .plantingArea(request.plantingArea())
                            .expectedWeight(request.expectedWeight())
                    .build());
            return true;
        } catch (Exception e) {
            log.error("Encountered an error while saving planted data", e);
        }
        return false;
    }

    @Override
    public Boolean harvest(HarvestRequest request) {
        var name = request.farmName();
        var crop = request.cropType();
        var farmEntity = farmRepository.findFarmByNameAndCrop(name, crop);
        if (Objects.nonNull(farmEntity)) {
            farmEntity.setActualWeight(request.actualHarvestedWeight());
            try {
                farmRepository.save(farmEntity);
                return true;
            } catch (Exception e) {
                log.error("Encountered an error while updating harvested data: farmName={}, cropType={}", name, crop, e);
            }
        } else {
            log.warn("farmName={} with cropType={} is non-existent from our records", name, crop);
        }
        return false;
    }

    @Override
    public List<Farm> farmBySeason(Season season) {
        return farmRepository.findAllBySeason(season);
    }
}
