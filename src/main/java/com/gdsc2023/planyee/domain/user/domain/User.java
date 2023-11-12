package com.gdsc2023.planyee.domain.user.domain;

import com.gdsc2023.planyee.domain.category.domain.Category;
import com.gdsc2023.planyee.domain.place.domain.Place;
import com.gdsc2023.planyee.domain.plan.domain.Plan;
import jakarta.persistence.*;

import com.gdsc2023.planyee.domain.common.BaseEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Entity
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, updatable = false)
    private Long id;

    @Column(unique = true, nullable = false)
    private String oauthId;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    @Column(nullable = false)
    private Integer birthYear;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Plan> planList;

    @ManyToMany
    @JoinTable(
            name = "user_place",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "place_id")
    )
    @Setter
    private List<Place> preferredPlaces;

    @Builder
    private User(String oauthId, String nickname, Gender gender, Integer birthYear, Role role) {
        this.oauthId = oauthId;
        this.nickname = nickname;
        this.gender = gender;
        this.birthYear = birthYear;
        this.role = role;
    }
}

