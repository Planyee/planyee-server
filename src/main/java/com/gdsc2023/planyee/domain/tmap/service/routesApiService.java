package com.gdsc2023.planyee.domain.tmap.service;


import com.gdsc2023.planyee.domain.place.domain.Place;
import com.gdsc2023.planyee.domain.place.repository.PlaceRepository;
import com.gdsc2023.planyee.domain.tmap.domain.ApiRequestParam;
import com.gdsc2023.planyee.domain.tmap.domain.ApiResponseParam.Coordinate;
import com.gdsc2023.planyee.domain.tmap.dto.PlaceDistanceDto;
import com.gdsc2023.planyee.domain.tmap.util.CalculationUtil;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class routesApiService {

    private final String API_URL = "https://apis.openapi.sk.com/tmap/routes";
    @Value("${tmap-api-key}")
    private String APP_KEY;

    @Autowired
    private PlaceRepository placeRepository;

    public ResponseEntity<String> requestCarRoutes(ApiRequestParam requestParam) {

        RestTemplate restTemplate = new RestTemplate();

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(API_URL)
                                                           .queryParam("version", "1");

        HttpHeaders headers = new HttpHeaders();

        headers.add("appKey", APP_KEY);
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        HttpEntity<ApiRequestParam> entity = new HttpEntity<>(requestParam, headers);

        return restTemplate.exchange(builder.toUriString(), HttpMethod.POST, entity, String.class);

    }

    public Map<String, BigDecimal> calculateDistanceWith(List<Coordinate> coordinates) {

        List<Place> places = placeRepository.findAll();

        Map<String, BigDecimal> result = new HashMap<>();


        for (Place place : places) {
            String name = place.getName();
            BigDecimal placeLatitude = place.getLatitude();
            BigDecimal placeLongitude = place.getLongitude();

            for (Coordinate coordinate : coordinates) {
                BigDecimal pointLatitude = coordinate.getLatitude();
                BigDecimal pointLongitude = coordinate.getLongitude();

                BigDecimal distance = CalculationUtil.calculateDistance(placeLatitude, placeLongitude, pointLongitude, pointLatitude);

                if(!result.containsKey(name)) {
                    result.put(name, distance);
                } else if(result.get(name).compareTo(distance) > 0) {
                    result.put(name, distance);
                }
            }

        }

        return result;

    }




}
