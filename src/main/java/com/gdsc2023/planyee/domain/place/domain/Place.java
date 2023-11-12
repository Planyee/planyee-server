package com.gdsc2023.planyee.domain.place.domain;

import java.math.BigDecimal;
import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import com.gdsc2023.planyee.domain.category.domain.Category;
import com.gdsc2023.planyee.domain.common.BaseEntity;
import com.gdsc2023.planyee.domain.plan.domain.Plan;
import com.gdsc2023.planyee.domain.user.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
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

    @ManyToMany(mappedBy = "preferredPlaces")
    private List<User> userList;
}
