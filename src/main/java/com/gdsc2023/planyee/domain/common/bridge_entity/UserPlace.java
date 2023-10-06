package com.gdsc2023.planyee.domain.common.bridge_entity;


import com.gdsc2023.planyee.domain.category.domain.Category;
import com.gdsc2023.planyee.domain.place.domain.Place;
import com.gdsc2023.planyee.domain.user.domain.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "user_categories")
@NoArgsConstructor
public class UserPlace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, updatable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_place", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "place_user", referencedColumnName = "id")
    private Place place;
}
