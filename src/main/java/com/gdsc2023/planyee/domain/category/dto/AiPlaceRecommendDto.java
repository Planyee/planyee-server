package com.gdsc2023.planyee.domain.category.dto;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AiPlaceRecommendDto {

    public AiPlaceRecommendDto(String placeName, Long userId) {
        this.placeName = placeName;
        this.userId = userId;
    }

    public String placeName;
    public Long userId;

}
