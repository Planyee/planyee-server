package com.gdsc2023.planyee.domain.tmap.service;


import com.gdsc2023.planyee.domain.tmap.domain.TmapApiRequestParam;
import com.nimbusds.jose.shaded.gson.JsonObject;
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
public class TmapApiRoutesService {

    private final String API_URL = "https://apis.openapi.sk.com/tmap/routes";
    @Value("${tmap-api-key}")
    private String APP_KEY;

    public ResponseEntity<String> requestCarRoutes(TmapApiRequestParam requestParam) {
        RestTemplate restTemplate = new RestTemplate();
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(API_URL)
                                                           .queryParam("version", "1");
        HttpHeaders headers = new HttpHeaders();
        headers.add("appKey", APP_KEY);
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);


        HttpEntity<TmapApiRequestParam> entity = new HttpEntity<>(requestParam, headers);

        return restTemplate.exchange(builder.toUriString(), HttpMethod.POST, entity, String.class);

    }


}
