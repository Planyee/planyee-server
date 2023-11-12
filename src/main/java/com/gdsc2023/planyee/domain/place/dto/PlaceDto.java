package com.gdsc2023.planyee.domain.place.dto;

import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PlaceDto {
    private String name;
    private Set<String> mainCategory;
    private Set<String> subCategory;
    private String address;
    private String description;
    private String etc;
}