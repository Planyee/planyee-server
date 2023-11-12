package com.gdsc2023.planyee.domain.tmap.dto;

import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserRouteDto {

    StartPoint startPoint;
    List<MileStone> mileStones;
    EndPoint endPoint;

    public UserRouteDto(StartPoint startPoint, List<MileStone> mileStones, EndPoint endPoint) {
        this.startPoint = startPoint;
        this.mileStones = mileStones;
        this.endPoint = endPoint;
    }


}
