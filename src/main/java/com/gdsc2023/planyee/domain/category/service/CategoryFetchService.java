package com.gdsc2023.planyee.domain.category.service;


import com.gdsc2023.planyee.domain.category.domain.Category;
import com.gdsc2023.planyee.domain.category.dto.AiPlaceRecommendDto;
import com.gdsc2023.planyee.domain.category.dto.CategoryRecommendDto;
import com.gdsc2023.planyee.domain.category.repository.CategoryRepository;
import com.gdsc2023.planyee.domain.place.domain.Place;
import com.gdsc2023.planyee.domain.place.repository.PlaceRepository;
import com.gdsc2023.planyee.domain.user.domain.User;
import com.gdsc2023.planyee.domain.user.repository.UserRepository;
import com.gdsc2023.planyee.global.config.oauth.SessionUser;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryFetchService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PlaceRepository placeRepository;



    public List<CategoryRecommendDto> getCategoryRecommendationOneByOne() {
        List<Place> places = placeRepository.findRandomPlaceByCategories();


        List<CategoryRecommendDto> recommends = places.stream()
                .map(place -> new CategoryRecommendDto(place.getId(), place.getImageUrl()))
                .toList();
        return recommends;
    }

    public AiPlaceRecommendDto createAiPlaceRecommendDto(SessionUser user, Long placeId) {

        Optional<User> currentUser = userRepository.findByOauthId(user.getOauthId());
        Optional<Place> selectedPlace = placeRepository.findById(placeId);
        if (currentUser.isEmpty()) {
            // 예외처리
        }
        if (selectedPlace.isEmpty()) {
            // 예외처리
        }

        return new AiPlaceRecommendDto(selectedPlace.get().getName(),
                currentUser.get().getId());

    }



}
