package com.gdsc2023.planyee.domain.tmap.service;


import com.gdsc2023.planyee.domain.tmap.domain.apiRequestParam;
import com.gdsc2023.planyee.domain.tmap.domain.apiResponseParam.Coordinate;
import java.util.List;
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

    public ResponseEntity<String> requestCarRoutes(apiRequestParam requestParam) {

        RestTemplate restTemplate = new RestTemplate();

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(API_URL)
                                                           .queryParam("version", "1");

        HttpHeaders headers = new HttpHeaders();

        headers.add("appKey", APP_KEY);
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        HttpEntity<apiRequestParam> entity = new HttpEntity<>(requestParam, headers);

        return restTemplate.exchange(builder.toUriString(), HttpMethod.POST, entity, String.class);

    }

    public ResponseEntity<String> calculateDistance(List<Coordinate> coordinates) {

    }


}
