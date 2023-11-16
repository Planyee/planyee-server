package com.gdsc2023.planyee.domain.place.dto;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Coordinate {
    private BigDecimal latitude;
    private BigDecimal longitude;
}
