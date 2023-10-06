package com.gdsc2023.planyee.domain.common.bridge_entity;

import com.gdsc2023.planyee.domain.category.domain.Category;
import com.gdsc2023.planyee.domain.place.domain.Place;
import com.gdsc2023.planyee.domain.plan.domain.Plan;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "plan_places")
@NoArgsConstructor
public class PlanPlace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, updatable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "plan_place", referencedColumnName = "id")
    private Plan plan;

    @ManyToOne
    @JoinColumn(name = "place_plan", referencedColumnName = "id")
    private Place place;

}
