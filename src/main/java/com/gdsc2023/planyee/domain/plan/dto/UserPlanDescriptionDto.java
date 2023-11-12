package com.gdsc2023.planyee.domain.plan.dto;

import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserPlanDescriptionDto {
    private List<String> planPreferedPlaces;
    private String additionalCondition;
}
