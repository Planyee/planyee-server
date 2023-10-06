package com.gdsc2023.planyee.domain.common;


import com.gdsc2023.planyee.domain.category.domain.Category;
import com.gdsc2023.planyee.domain.place.domain.Place;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "place_categories")
@NoArgsConstructor
public class PlaceCategory extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, updatable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "place_category", referencedColumnName = "id")
    private Place place;

    @ManyToOne
    @JoinColumn(name = "category_place", referencedColumnName = "id")
    private Category category;

    @Builder
    public PlaceCategory(Place place, Category category) {
        this.place = place;
        this.category = category;
    }
}
