package com.gdsc2023.planyee.domain.tmap.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.gdsc2023.planyee.domain.place.domain.Place;
import com.gdsc2023.planyee.domain.place.service.PlaceService;
import com.gdsc2023.planyee.domain.tmap.domain.TmapRoutesRequest;
import com.gdsc2023.planyee.domain.tmap.domain.TmapRoutesResponse.Coordinate;
import com.gdsc2023.planyee.global.util.DistanceUtil;

@Service
public class RoutesApiService {
    private static final String API_URL = "https://apis.openapi.sk.com/tmap/routes?version=1";
    private final String APP_KEY;
    private final RestTemplate restTemplate;
    private final PlaceService placeService;

    public RoutesApiService(
            @Value("${tmap-api-key}") String APP_KEY, RestTemplate restTemplate, PlaceService placeService) {
        this.APP_KEY = APP_KEY;
        this.restTemplate = restTemplate;
        this.placeService = placeService;
    }

    public ResponseEntity<String> requestRoutes(TmapRoutesRequest tmapRoutesRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("appKey", APP_KEY);
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        HttpEntity<TmapRoutesRequest> entity = new HttpEntity<>(tmapRoutesRequest, headers);
        ResponseEntity<String> response = restTemplate.exchange(API_URL, HttpMethod.POST, entity, String.class);

        return response;
    }

    public Map<String, BigDecimal> calculateDistance(List<Coordinate> coordinates) {
        List<Place> places = placeService.findAllPlaces();

        Map<String, BigDecimal> result = new HashMap<>();

        for (Place place : places) {
            String name = place.getName();
            BigDecimal placeLatitude = place.getLatitude();
            BigDecimal placeLongitude = place.getLongitude();

            for (Coordinate coordinate : coordinates) {
                BigDecimal pointLatitude = coordinate.getLatitude();
                BigDecimal pointLongitude = coordinate.getLongitude();

                BigDecimal distance = DistanceUtil.calculateDistance(
                        placeLatitude, placeLongitude, pointLatitude, pointLongitude);

                if (!result.containsKey(name)) {
                    result.put(name, distance);
                } else if (result.get(name).compareTo(distance) > 0) {
                    result.put(name, distance);
                }
            }
        }

        return result;
    }
}
