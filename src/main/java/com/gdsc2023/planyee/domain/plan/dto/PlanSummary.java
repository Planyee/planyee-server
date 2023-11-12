package com.gdsc2023.planyee.domain.plan.dto;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PlanSummary {
    private Long planId;
    private String planName;
    private LocalDate date;
}
