package com.gdsc2023.planyee.domain.place.dto;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import com.gdsc2023.planyee.domain.place.domain.Place;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class PlaceDto {
    private String name;
    private Set<String> mainCategory;
    private Set<String> subCategory;
    private String address;
    private String description;
    private String etc;

    public static PlaceDto create(Place place, List<String> mainCategories, List<String> subCategories) {
        return new PlaceDto(
                place.getName(),
                new HashSet<>(mainCategories),
                new HashSet<>(subCategories),
                place.getAddress(),
                place.getDescription(),
                place.getEtc()
        );
    }
}