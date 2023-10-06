package com.gdsc2023.planyee.domain.category.domain;


import com.gdsc2023.planyee.domain.common.PlaceCategory;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Entity
@Table(name = "categories")
@NoArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, updatable = false)
    private Long id;

    @Column(length = 20)
    private String name;

    @OneToMany
    private List<PlaceCategory> placeList;

    @Builder
    public Category(String name) {
        this.name = name;
    }
}
