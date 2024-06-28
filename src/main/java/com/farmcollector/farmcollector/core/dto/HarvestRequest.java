package com.farmcollector.farmcollector.core.dto;

public record HarvestRequest(
    String farmName,
    String cropType,
    double actualHarvestedWeight
) {
}
