package com.gdsc2023.planyee.domain.plan.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlanCreateRequest {
    private LocalDate date;
    private String planName;
    private BigDecimal sourceLatitude;
    private BigDecimal sourceLongitude;
    private BigDecimal destinationLatitude;
    private BigDecimal destinationLongitude;
    private List<String> planPreferredPlaces;
    private String additionalCondition;
}
