package com.gdsc2023.planyee.domain.tmap.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gdsc2023.planyee.domain.tmap.domain.apiResponseParam.Coordinate;
import com.gdsc2023.planyee.domain.tmap.domain.apiResponseParam.Feature;
import com.gdsc2023.planyee.domain.tmap.domain.apiResponseParam.FeatureCollection;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ApiResponseParserUtil {

    public static List<Coordinate> ParseToCoordinates(ResponseEntity<String> apiResponse)
            throws JsonProcessingException {

        String body = apiResponse.getBody();

        ObjectMapper objectMapper = new ObjectMapper();

        FeatureCollection fc = objectMapper.readValue(body, FeatureCollection.class);

        List<Coordinate> coordinates = new ArrayList<>();

        List<Feature>  features   =  fc.getFeatures();

        features.forEach(feature -> coordinates.addAll(feature.getGeometry().getCoordinates()));


        return coordinates;

    }

}
