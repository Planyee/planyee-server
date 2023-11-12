package com.gdsc2023.planyee.domain.category.domain;

import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import com.gdsc2023.planyee.domain.place.domain.Place;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
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
}
