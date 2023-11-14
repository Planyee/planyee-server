package com.gdsc2023.planyee.domain.place.controller;

import jakarta.servlet.http.HttpSession;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.gdsc2023.planyee.domain.place.dto.PlaceNameWithImageUrl;
import com.gdsc2023.planyee.domain.place.service.PlaceRecommendService;
import com.gdsc2023.planyee.global.config.oauth.LoginUser;
import com.gdsc2023.planyee.global.config.oauth.SessionUser;
import lombok.RequiredArgsConstructor;

@Slf4j
@RequiredArgsConstructor
@RestController
public class PlaceRecommendController {
    private final PlaceRecommendService placeRecommendService;
    private final HttpSession httpSession;
    private final Logger logger;

    @GetMapping("/select")
    public List<PlaceNameWithImageUrl> getPlaceInEachCategory() {
        return placeRecommendService.getPlaceInEachCategory();
    }

    @PostMapping("/select")
    public void postUserPreferredPlaces(@RequestBody List<String> userPreferredPlaceNames) {
        logger.info("===============" + httpSession.getId() + "===============");
        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        placeRecommendService.saveUserPreferredPlaces(user.getId(), userPreferredPlaceNames);
    }
}
