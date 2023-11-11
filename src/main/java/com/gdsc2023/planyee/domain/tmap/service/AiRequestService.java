package com.gdsc2023.planyee.domain.tmap.service;

import com.gdsc2023.planyee.domain.tmap.domain.ApiRequestParam;
import com.gdsc2023.planyee.domain.tmap.domain.apiResponseParam.Coordinate;
import com.gdsc2023.planyee.domain.tmap.dto.PlaceDistanceDto;
import java.util.List;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class AiRequestService {

    private static final String API_URL = "";

    public ResponseEntity<String> requestMileStonesWith(List<PlaceDistanceDto> placeDistances) {
        RestTemplate restTemplate = new RestTemplate();

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(API_URL);

        HttpHeaders headers = new HttpHeaders();


        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        HttpEntity<PlaceDistanceDto> entity = new HttpEntity<>(placeDistances, headers);

        return restTemplate.exchange(builder.toUriString(), HttpMethod.POST, entity, String.class);
    }
}
