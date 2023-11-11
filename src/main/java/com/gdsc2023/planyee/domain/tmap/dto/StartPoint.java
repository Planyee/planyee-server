package com.gdsc2023.planyee.domain.tmap.dto;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class StartPoint {

    public BigDecimal latitude;
    public BigDecimal longitude;

    public StartPoint(BigDecimal latitude, BigDecimal longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
