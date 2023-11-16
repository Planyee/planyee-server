package com.gdsc2023.planyee.domain.place.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.gdsc2023.planyee.domain.place.domain.Place;
import com.gdsc2023.planyee.domain.place.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class PlaceService {
    private final PlaceRepository placeRepository;

    public List<Place> findUserPrefferedPlaces(Long userId) {
        return placeRepository.findPreferredPlaceByUserId(userId);
    }

    public List<Place> findPlacesWithName(List<String> placeNames) {
        return placeRepository.findByNameIn(placeNames);
    }

    public List<Place> findRandomPlaceEachCategories() {
        return placeRepository.findRandomPlaceEachCategories();
    }

    public List<Place> findAllPlaces() {
        return placeRepository.findAll();
    }
}
