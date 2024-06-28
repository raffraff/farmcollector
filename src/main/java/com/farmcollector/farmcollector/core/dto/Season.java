package com.farmcollector.farmcollector.core.dto;

public enum Season {
    SPRING,
    SUMMER,
    FALL,
    WINTER;

    public static Season getByName(String name) {
        for (Season season : values()) {
            if (season.name().equalsIgnoreCase(name)) {
                return season;
            }
        }
        throw new IllegalArgumentException("Invalid season");
    }
}
