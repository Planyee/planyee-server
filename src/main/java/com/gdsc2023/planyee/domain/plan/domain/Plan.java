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
@Table(name = "plans")
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
            joinColumns = @JoinColumn(name = "place_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
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

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private LocalDateTime startTime;

    @Column(nullable = false)
    private LocalDateTime arrivalTime;

    @Builder
    public Plan(String name, User user, BigDecimal sourceLatitude, BigDecimal sourceLongitude, BigDecimal destinationLatitude, BigDecimal destinationLongitude, LocalDate date, LocalDateTime startTime, LocalDateTime arrivalTime) {
        this.name = name;
        this.user = user;
        this.sourceLatitude = sourceLatitude;
        this.sourceLongitude = sourceLongitude;
        this.destinationLatitude = destinationLatitude;
        this.destinationLongitude = destinationLongitude;
        this.date = date;
        this.startTime = startTime;
        this.arrivalTime = arrivalTime;
    }
}
