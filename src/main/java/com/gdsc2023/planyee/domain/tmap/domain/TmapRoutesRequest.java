package com.gdsc2023.planyee.domain.tmap.domain;

import java.math.BigDecimal;
import jakarta.validation.constraints.NotNull;
import com.gdsc2023.planyee.domain.plan.dto.PlanCreateRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TmapRoutesRequest {
    @NotNull
    private BigDecimal endX;
    @NotNull
    private BigDecimal endY;
    @NotNull
    private BigDecimal startX;
    @NotNull
    private BigDecimal startY;

    public static TmapRoutesRequest create(PlanCreateRequest request) {
        return new TmapRoutesRequest(request.getDestinationLatitude(), request.getDestinationLongitude(),
                request.getSourceLatitude(), request.getSourceLongitude());
    }
}
