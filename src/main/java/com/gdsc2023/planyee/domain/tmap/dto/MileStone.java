package com.gdsc2023.planyee.domain.tmap.dto;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class MileStone {

    public BigDecimal latitude;
    public BigDecimal longitude;
    public String placeDescription;

    public MileStone(BigDecimal latitude, BigDecimal longitude, String placeDescription) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.placeDescription = placeDescription;
    }
}
