package com.gdsc2023.planyee.domain.tmap.dto;

import com.gdsc2023.planyee.domain.place.domain.Place;
import java.util.List;
import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class AiResponseParam {

    private List<String> placeNames;


}
