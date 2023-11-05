package com.gdsc2023.planyee.domain.place.domain;

import com.gdsc2023.planyee.domain.category.domain.Category;
import com.gdsc2023.planyee.domain.common.BaseEntity;
import com.gdsc2023.planyee.domain.plan.domain.Plan;
import com.gdsc2023.planyee.domain.user.domain.User;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;


@Getter
@Entity
@NoArgsConstructor
public class Place extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, updatable = false)
    private Long id;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(precision = 11, scale = 8, nullable = true)
    private BigDecimal latitude;

    @Column(precision = 11, scale = 8, nullable = true)
    private BigDecimal longitude;

    @Column(nullable = false, length = 100)
    private String address;

    @Column(length = 255)
    private String imageUrl;

    @Column
    private String description;

    @Column
    private String review;

    @Column
    private String etc;


    @ManyToMany
    @JoinTable(
        name = "place_category",
        joinColumns = @JoinColumn(name = "place_id"),
        inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categoryList;

    @ManyToMany(mappedBy = "placeList")
    private List<Plan> planList;

    @Builder
    public Place(String name, BigDecimal latitude, BigDecimal longitude, String address, String imageUrl,
        String description, String review, String etc) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
        this.imageUrl = imageUrl;
        this.description = description;
        this.review = review;
        this.etc = etc;
    }
}
