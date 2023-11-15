package com.gdsc2023.planyee.domain.place.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.gdsc2023.planyee.domain.place.domain.Place;

public interface PlaceRepository extends JpaRepository<Place, Long> {
    @Query(value = "SELECT *\n"
            + "FROM (\n"
            + "    SELECT *, ROW_NUMBER() OVER (PARTITION BY pc.category_id ORDER BY RAND()) as rn\n"
            + "    FROM place\n"
            + "    JOIN place_category pc ON place.id = pc.place_id\n"
            + ") as random_places\n"
            + "WHERE rn = 1;", nativeQuery = true)
    List<Place> findRandomPlaceEachCategories();

    @Query(value = "SELECT * FROM place JOIN user_place ON place.id = user_place.place_id WHERE user.id = :userId", nativeQuery = true)
    List<Place> findPreferredPlaceByUserId(@Param("userId") Long userId);

    List<Place> findByNameIn(List<String> userPreferredPlaceNames);
}
