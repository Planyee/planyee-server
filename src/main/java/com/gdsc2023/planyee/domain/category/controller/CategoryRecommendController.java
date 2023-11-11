package com.gdsc2023.planyee.domain.category.controller;


import com.gdsc2023.planyee.domain.category.domain.Category;
import com.gdsc2023.planyee.domain.category.dto.CategoryRecommendDto;
import com.gdsc2023.planyee.domain.category.service.CategoryFetchService;
import com.gdsc2023.planyee.global.config.oauth.LoginUser;
import com.gdsc2023.planyee.global.config.oauth.SessionUser;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryRecommendController {

    @Autowired
    CategoryFetchService categoryFetchService;


//    public List<CategoryRecommendDto> showCategoryRecommend(@LoginUser SessionUser user) {
//
//        List<Category> categories = categoryFetchService.getAll();
//        List<CategoryRecommendDto> ret = new ArrayList<>();
//        for (Category category : categories) {
//
//        }
//    }


}
