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
@Table(name = "places")
@NoArgsConstructor
public class Place extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, updatable = false)
    private Long id;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(nullable = false)
    private BigDecimal latitude;

    @Column(nullable = false)
    private BigDecimal longitude;

    @ManyToMany
    @JoinTable(
            name = "place_category",
            joinColumns = @JoinColumn(name = "place_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categoryList;

    @ManyToMany(mappedBy = "preferredPlaces")
    private List<User> userList;

    @ManyToMany(mappedBy = "placeList")
    private List<Plan> planList;

    @Builder
    public Place(String name, BigDecimal latitude, BigDecimal longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
