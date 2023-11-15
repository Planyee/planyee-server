package com.gdsc2023.planyee.domain.ai.domain;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class AiRecommendRequest {
        List<String> userPreferredPlaces;
        List<String> planPreferredPlaces;
        String additionalCondition;
        Map<String, BigDecimal> allPlaces;

    public AiRecommendRequest(List<String> userPreferredPlaces, List<String> planPreferredPlaces,
                              String additionalCondition,
                              Map<String, BigDecimal> allPlaces) {
        this.userPreferredPlaces = userPreferredPlaces;
        this.planPreferredPlaces = planPreferredPlaces;
        this.additionalCondition = additionalCondition;
        this.allPlaces = allPlaces;
    }
}
