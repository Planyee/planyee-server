package com.gdsc2023.planyee.domain.tmap.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class PlaceDistanceDto {

        List<String> userPrefferedPlaces;
        List<String> planPrefferedPlaces;
        String additionalCondition;
        Map<String, BigDecimal> distances;

    public PlaceDistanceDto(List<String> userPrefferedPlaces, List<String> planPrefferedPlaces,
                            String additionalCondition,
                            Map<String, BigDecimal> distances) {
        this.userPrefferedPlaces = userPrefferedPlaces;
        this.planPrefferedPlaces = planPrefferedPlaces;
        this.additionalCondition = additionalCondition;
        this.distances = distances;
    }
}
