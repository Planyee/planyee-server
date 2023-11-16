package com.gdsc2023.planyee.domain.plan.dto;

import java.util.List;
import com.gdsc2023.planyee.domain.place.dto.Coordinate;
import com.gdsc2023.planyee.domain.place.dto.PlaceDto;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class PlanDetail {
    private Coordinate source;
    private Coordinate destination;
    private List<PlaceDto> recommendations;
}
