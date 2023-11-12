package com.gdsc2023.planyee.domain.plan.domain;

import com.gdsc2023.planyee.domain.common.BaseEntity;
import com.gdsc2023.planyee.domain.place.domain.Place;
import com.gdsc2023.planyee.domain.user.domain.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Getter
@Entity
@NoArgsConstructor
public class Plan extends BaseEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, updatable = false)
    private Long id;

    @Column(length = 50, nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToMany
    @JoinTable(
            name = "plan_place",
            joinColumns = @JoinColumn(name = "plan_id"),
            inverseJoinColumns = @JoinColumn(name = "place_id")
    )
    private List<Place> placeList;

    @Column(nullable = false)
    private BigDecimal sourceLatitude;

    @Column(nullable = false)
    private BigDecimal sourceLongitude;

    @Column(nullable = false)
    private BigDecimal destinationLatitude;

    @Column(nullable = false)
    private BigDecimal destinationLongitude;

    @Column
    private LocalDate date;

    @Column
    private String additionalDescription;

    @Builder
    public Plan(String name, User user, List<Place> placeList, BigDecimal sourceLatitude, BigDecimal sourceLongitude,
                BigDecimal destinationLatitude, BigDecimal destinationLongitude, LocalDate date,
                String additionalDescription) {
        this.name = name;
        this.user = user;
        this.placeList = placeList;
        this.sourceLatitude = sourceLatitude;
        this.sourceLongitude = sourceLongitude;
        this.destinationLatitude = destinationLatitude;
        this.destinationLongitude = destinationLongitude;
        this.date = date;
        this.additionalDescription = additionalDescription;
    }
}
