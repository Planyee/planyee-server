package com.gdsc2023.planyee.domain.ai.service;

import java.math.BigDecimal;
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
import com.gdsc2023.planyee.domain.ai.domain.AiRecommendRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AiRecommendService {
    @Value("${ai-url}")
    private final String API_URL;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public List<String> requestRecommendPlaces(Map<String, BigDecimal> placeDistances,
                                               List<String> userPreferredPlaces,
                                               List<String> planPreferredPlaces,
                                               String additionalCondition) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        AiRecommendRequest request = new AiRecommendRequest(
                userPreferredPlaces, planPreferredPlaces, additionalCondition, placeDistances);

        HttpEntity<AiRecommendRequest> entity = new HttpEntity<>(request, headers);
        ResponseEntity<String> response = restTemplate.exchange(API_URL, HttpMethod.POST, entity, String.class);

        return convertJsonToList(response);
    }

    public List<String> convertJsonToList(ResponseEntity<String> response) {
        try {
            String json = response.getBody();
            List<String> list = objectMapper.readValue(json, new TypeReference<>() {});
            return list;
        } catch (JsonProcessingException e) {
            throw new RuntimeException();
        }
    }
}
