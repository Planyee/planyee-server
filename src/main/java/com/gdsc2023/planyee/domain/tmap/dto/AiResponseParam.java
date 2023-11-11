package com.gdsc2023.planyee.domain.tmap.dto;

import com.gdsc2023.planyee.domain.place.domain.Place;
import java.util.List;
import lombok.Getter;


@Getter
public class AiResponseParam {

    List<Long> placeIds;

    public AiResponseParam(List<Long> placeIds) {
        this.placeIds = placeIds;
    }
}
