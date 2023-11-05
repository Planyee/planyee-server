package com.gdsc2023.planyee.domain.tmap.domain;


import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class apiRequestParam {

    @NotNull
    private BigDecimal endX;
    @NotNull
    private BigDecimal endY;
    @NotNull
    private BigDecimal startX;
    @NotNull
    private BigDecimal startY;

}
