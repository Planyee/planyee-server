package com.gdsc2023.planyee.domain.place.service;

import com.gdsc2023.planyee.domain.place.domain.Place;
import com.gdsc2023.planyee.domain.place.dto.PlaceNameWithImageUrl;
import com.gdsc2023.planyee.domain.place.repository.PlaceRepository;
import com.gdsc2023.planyee.domain.user.domain.User;
import com.gdsc2023.planyee.domain.user.repository.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class PlaceRecommendService {
    private final UserRepository userRepository;
    private final PlaceRepository placeRepository;

    public List<PlaceNameWithImageUrl> getPlaceInEachCategory() {
        List<Place> randomPlaceByCategories = placeRepository.findRandomPlaceByCategories();

        List<PlaceNameWithImageUrl> randomPlaceList = randomPlaceByCategories.stream()
                .map((place) -> new PlaceNameWithImageUrl(place.getName(), place.getImageUrl()))
                .toList();

        return randomPlaceList;
    }

    @Transactional
    public void saveUserPrefferedPlaces(Long userId, List<String> userPrefferedPlaceNames) {
        User user = userRepository.findById(userId).get();
        List<Place> userPrefferedPlaces = placeRepository.findByNameIn(userPrefferedPlaceNames);
        user.setPreferredPlaces(userPrefferedPlaces);
    }
}
