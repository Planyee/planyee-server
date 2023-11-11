package com.gdsc2023.planyee.domain.category.dto;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CategoryRecommendDto {

    public Long id;
    public String imageUrl;

    public CategoryRecommendDto(Long id, String imageUrl) {
        this.id = id;
        this.imageUrl = imageUrl;
    }
}
