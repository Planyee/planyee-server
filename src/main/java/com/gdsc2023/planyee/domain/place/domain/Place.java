package com.gdsc2023.planyee.domain.place.domain;

import com.gdsc2023.planyee.domain.common.BaseEntity;
import com.gdsc2023.planyee.domain.common.bridge_entity.PlaceCategory;
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

    @OneToMany(mappedBy = "place")
    private List<PlaceCategory> categoryList;

    @OneToMany(mappedBy = "place")
    private List<User> userList;

    @Builder
    public Place(String name, BigDecimal latitude, BigDecimal longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
