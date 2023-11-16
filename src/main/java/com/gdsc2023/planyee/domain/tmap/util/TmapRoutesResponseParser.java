package com.gdsc2023.planyee.domain.tmap.util;

import java.util.ArrayList;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import com.gdsc2023.planyee.domain.tmap.domain.TmapRoutesResponse.Coordinate;
import com.gdsc2023.planyee.domain.tmap.domain.TmapRoutesResponse.Feature;
import com.gdsc2023.planyee.domain.tmap.domain.TmapRoutesResponse.FeatureCollection;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class TmapRoutesResponseParser {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static List<Coordinate> ParseToCoordinates(ResponseEntity<String> response) throws JsonProcessingException {
        String body = response.getBody();
        FeatureCollection featureCollection = objectMapper.readValue(body, FeatureCollection.class);

        List<Coordinate> coordinates = new ArrayList<>();

        List<Feature> features = featureCollection.getFeatures();
        features.forEach(feature -> coordinates.addAll(feature.getGeometry().getCoordinates()));

        return coordinates;
    }
}
