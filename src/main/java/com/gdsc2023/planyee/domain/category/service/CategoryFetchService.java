package com.gdsc2023.planyee.domain.category.service;


import com.gdsc2023.planyee.domain.category.domain.Category;
import com.gdsc2023.planyee.domain.category.repository.CategoryRepository;
import com.gdsc2023.planyee.domain.place.repository.PlaceRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryFetchService {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    PlaceRepository placeRepository;

    public List<Category> getAll() {
        return categoryRepository.findAll();
    }
//
//    public List<Category> getAllCategoryImage() {
//        List<Category> categories = categoryRepository.findAll();
//
//    }



}
