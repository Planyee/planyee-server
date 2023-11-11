package com.gdsc2023.planyee.domain.tmap.service;

import com.gdsc2023.planyee.domain.place.domain.Place;
import com.gdsc2023.planyee.domain.place.repository.PlaceRepository;
import com.gdsc2023.planyee.domain.tmap.dto.MileStone;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class MileStoneDtoFactoryService {


    private PlaceRepository placeRepository;

    public List<MileStone> createMileStonesWith(List<Long> placeIds) {

        List<MileStone> mileStones = new ArrayList<>();

        List<Place> milestonePlaces = placeRepository.findAllById(placeIds);
        for (Place place : milestonePlaces) {
            MileStone milestone = MileStone.builder()
                    .latitude(place.getLatitude())
                    .longitude(place.getLongitude())
                    .placeDescription(place.getDescription())
                    .build();
            MileStone mileStone = milestone;
            mileStones.add(milestone);

        }

        return mileStones;



    }


}