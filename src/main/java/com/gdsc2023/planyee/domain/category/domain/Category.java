package com.gdsc2023.planyee.domain.category.domain;

import com.gdsc2023.planyee.domain.place.domain.Place;
import com.gdsc2023.planyee.domain.user.domain.User;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, updatable = false)
    private Long id;

    @Column(length = 50)
    private String name;

    @Column(length = 20)
    private String mainCategory;

    @ManyToMany(mappedBy = "categoryList")
    private List<Place> placeList;

    @Builder
    public Category(String name, String mainCategory) {
        this.name = name;
        this.mainCategory = mainCategory;
    }
}
