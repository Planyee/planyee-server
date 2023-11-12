package com.gdsc2023.planyee.domain.place.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.gdsc2023.planyee.domain.place.domain.Place;
import com.gdsc2023.planyee.domain.place.dto.PlaceNameWithImageUrl;
import com.gdsc2023.planyee.domain.user.domain.User;
import com.gdsc2023.planyee.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class PlaceRecommendService {
    private final UserService userService;
    private final PlaceService placeService;

    public List<PlaceNameWithImageUrl> getPlaceInEachCategory() {
        List<Place> randomPlaceByCategories = placeService.findRandomPlaceEachCategories();

        List<PlaceNameWithImageUrl> randomPlaceList = randomPlaceByCategories.stream()
                .map((place) -> new PlaceNameWithImageUrl(place.getName(), place.getImageUrl()))
                .toList();

        return randomPlaceList;
    }

    @Transactional
    public void saveUserPreferredPlaces(Long userId, List<String> userPreferredPlaceNames) {
        User user = userService.getUser(userId);
        List<Place> userPreferredPlaces = placeService.findPlacesWithName(userPreferredPlaceNames);
        user.setPreferredPlaces(userPreferredPlaces);
    }
}
