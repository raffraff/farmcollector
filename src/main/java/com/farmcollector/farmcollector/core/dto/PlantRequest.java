package com.farmcollector.farmcollector.core.dto;

public record PlantRequest(
    String farmName,
    Season season,
    String cropType,
    double plantingArea,
    double expectedWeight
) {
}
