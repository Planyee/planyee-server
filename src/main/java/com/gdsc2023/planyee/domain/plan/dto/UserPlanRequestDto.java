package com.gdsc2023.planyee.domain.plan.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import lombok.Getter;
import org.springframework.web.bind.annotation.RestController;

@Getter
public class UserPlanRequestDto {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate date;
    private String planName;
    private BigDecimal sourceLatitude;
    private BigDecimal sourceLongitude;
    private BigDecimal destinationLatitude;
    private BigDecimal destinationLongitude;
    private List<String> planPreferedPlaces;
    private String additionalCondition;

    // 생성자, getter, setter 생략
}
