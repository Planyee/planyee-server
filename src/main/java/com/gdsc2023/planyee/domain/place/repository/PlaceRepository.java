package com.gdsc2023.planyee.domain.place.repository;

import com.gdsc2023.planyee.domain.place.domain.Place;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface PlaceRepository extends JpaRepository<Place, Long> {

    List<Place> findAll();
    @Query(value = "SELECT *\n"
            + "FROM (\n"
            + "    SELECT *, ROW_NUMBER() OVER (PARTITION BY pc.category_id ORDER BY RAND()) as rn\n"
            + "    FROM place\n"
            + "    JOIN place_category pc ON place.id = pc.place_id\n"
            + ") as random_places\n"
            + "WHERE rn = 1;", nativeQuery = true)
    List<Place> findRandomPlaceByCategories();

    @Query(value = "SELECT place FROM place JOIN user_place ON place.id = user_place.place_id WHERE user.id = :userId", nativeQuery = true)
    List<Place> findPrefferedPlaceByUserId(@Param("userId") Long userId);
}
