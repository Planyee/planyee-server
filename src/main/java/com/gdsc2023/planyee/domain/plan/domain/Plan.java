package com.gdsc2023.planyee.domain.plan.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import com.gdsc2023.planyee.domain.common.BaseEntity;
import com.gdsc2023.planyee.domain.place.domain.Place;
import com.gdsc2023.planyee.domain.plan.dto.PlanCreateRequest;
import com.gdsc2023.planyee.domain.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
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

    @Column(precision = 11, scale = 8, nullable = false)
    private BigDecimal sourceLatitude;

    @Column(precision = 11, scale = 8, nullable = false)
    private BigDecimal sourceLongitude;

    @Column(precision = 11, scale = 8, nullable = false)
    private BigDecimal destinationLatitude;

    @Column(precision = 11, scale = 8, nullable = false)
    private BigDecimal destinationLongitude;

    @Column
    private LocalDate date;

    @Column
    private String additionalDescription;

    public static Plan toEntity(User user, PlanCreateRequest request, List<Place> recommendPlaces) {
        return Plan.builder()
                .name(request.getPlanName())
                .user(user)
                .placeList(recommendPlaces)
                .sourceLatitude(request.getSourceLatitude())
                .sourceLongitude(request.getSourceLongitude())
                .destinationLatitude(request.getDestinationLatitude())
                .destinationLongitude(request.getDestinationLongitude())
                .date(request.getDate())
                .additionalDescription(request.getAdditionalCondition())
                .build();

    }
}
