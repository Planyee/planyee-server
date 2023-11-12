package com.gdsc2023.planyee.domain.place.controller;

import com.gdsc2023.planyee.domain.place.dto.PlaceNameWithImageUrl;
import com.gdsc2023.planyee.domain.place.service.PlaceRecommendService;
import com.gdsc2023.planyee.global.config.oauth.LoginUser;
import com.gdsc2023.planyee.global.config.oauth.SessionUser;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PlaceRecommendController {
    private final PlaceRecommendService placeRecommendService;

    @GetMapping("/select")
    public List<PlaceNameWithImageUrl> getPlaceInEachCategory() {
        return placeRecommendService.getPlaceInEachCategory();
    }

    @PostMapping("/select")
    public void postUserPrefferedPlaces(@LoginUser SessionUser user, List<String> userPrefferedPlaceNames) {
        placeRecommendService.saveUserPrefferedPlaces(user.getId(), userPrefferedPlaceNames);
    }
}
