package com.gdsc2023.planyee.domain.tmap.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.gdsc2023.planyee.domain.place.domain.Place;
import com.gdsc2023.planyee.domain.place.repository.PlaceRepository;
import com.gdsc2023.planyee.domain.tmap.domain.ApiRequestParam;
import com.gdsc2023.planyee.domain.tmap.domain.apiResponseParam.Coordinate;
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

    public List<PlaceDistanceDto> calculateDistanceWith(List<Coordinate> coordinates) {

        List<Place> places = placeRepository.findAll();

        Map<Long, BigDecimal> result = new HashMap<>();
        List<PlaceDistanceDto> placeDistanceList = new ArrayList<>();

        for (Place place : places) {
            Long placeId = place.getId();
            BigDecimal placeLatitude = place.getLatitude();
            BigDecimal placeLongitude = place.getLongitude();

            for (Coordinate coordinate : coordinates) {
                BigDecimal pointLatitude = coordinate.getLatitude();
                BigDecimal pointLongitude = coordinate.getLongitude();

                BigDecimal distance = CalculationUtil.calculateDistance(placeLatitude, placeLongitude, pointLongitude, pointLatitude);

                if(!result.containsKey(placeId)) {
                    result.put(placeId, distance);
                } else if(result.get(placeId).compareTo(distance) > 0) {
                    result.put(placeId, distance);
                }
            }

            PlaceDistanceDto placeDistanceDto = new PlaceDistanceDto();
            placeDistanceList.add(placeDistanceDto);
        }

        return placeDistanceList;

    }




}
