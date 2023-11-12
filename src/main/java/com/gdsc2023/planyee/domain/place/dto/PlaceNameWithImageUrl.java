package com.gdsc2023.planyee.domain.place.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PlaceNameWithImageUrl {
    private String name;
    private String imageUrl;
}
