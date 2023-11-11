package com.gdsc2023.planyee.domain.place.repository;

import com.gdsc2023.planyee.domain.place.domain.Place;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PlaceRepository extends JpaRepository<Place, Long> {
    List<Place> findAll();
}
