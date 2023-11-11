package com.gdsc2023.planyee.domain.place.repository;

import com.gdsc2023.planyee.domain.place.domain.Place;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface PlaceRepository extends JpaRepository<Place, Long> {

    List<Place> findAll();
    @Query(value = "SELECT * FROM (SELECT * FROM place JOIN place_category pc ON place.id = pc.place_id ORDER BY RAND()) AS randomized GROUP BY category_id", nativeQuery = true)
    List<Place> findRandomPlaceByCategories();

}
