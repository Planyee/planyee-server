package com.gdsc2023.planyee.domain.category.controller;


import com.gdsc2023.planyee.domain.category.domain.Category;
import com.gdsc2023.planyee.domain.category.dto.AiPlaceRecommendDto;
import com.gdsc2023.planyee.domain.category.dto.CategoryRecommendDto;
import com.gdsc2023.planyee.domain.category.service.CategoryFetchService;
import com.gdsc2023.planyee.domain.place.domain.Place;
import com.gdsc2023.planyee.global.config.oauth.LoginUser;
import com.gdsc2023.planyee.global.config.oauth.SessionUser;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryRecommendController {

    @Autowired
    CategoryFetchService categoryFetchService;


    @GetMapping
    public List<CategoryRecommendDto> showCategoryRecommend(@LoginUser SessionUser user) {

        return  categoryFetchService.getCategoryRecommendationOneByOne();
    }

    @PostMapping
    public AiPlaceRecommendDto giveUserPlaceChoiceToAi(@LoginUser SessionUser user, Long selectedPlaceId) {

        return categoryFetchService.createAiPlaceRecommendDto(user, selectedPlaceId);

    }


}
