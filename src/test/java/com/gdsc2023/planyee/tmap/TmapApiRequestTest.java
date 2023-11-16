package com.gdsc2023.planyee.tmap;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.gdsc2023.planyee.domain.ai.service.AiRecommendService;
import com.gdsc2023.planyee.domain.place.domain.Place;
import com.gdsc2023.planyee.domain.place.repository.PlaceRepository;
import com.gdsc2023.planyee.domain.tmap.domain.TmapRoutesRequest;
import com.gdsc2023.planyee.domain.tmap.domain.TmapRoutesResponse.Coordinate;
import com.gdsc2023.planyee.domain.tmap.domain.TmapRoutesResponse.Feature;
import com.gdsc2023.planyee.domain.tmap.domain.TmapRoutesResponse.FeatureCollection;
import com.gdsc2023.planyee.domain.tmap.service.RoutesApiService;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StopWatch;


@SpringBootTest
public class TmapApiRequestTest {


    private static final BigDecimal EARTH_RADIUS = new BigDecimal("6371000"); // 지구의 반지름 (미터 단위)
    private static final int SCALE = 5; // BigDecimal 연산의 정밀도;

    @Autowired
    RoutesApiService apiRoutesService;
    @Autowired
    AiRecommendService aiRecommendService;
    @Autowired
    PlaceRepository placeRepository;

    @Test
    @DisplayName("자동차 경로 api의 반환값 확인")
    void carRequestTest() throws Exception {

        TmapRoutesRequest requestParam = TmapRoutesRequest.builder()
                                                      .endX(new BigDecimal("126.9235355222221"))
                                                      .endY(new BigDecimal("37.460122514035135"))
                                                      .startX(new BigDecimal("126.88462351538156"))
                                                      .startY(new BigDecimal("37.47955275185523"))
                                                      .build();

        ResponseEntity<String> entity = apiRoutesService.requestRoutes(requestParam);


        String body = entity.getBody();

        ObjectMapper objectMapper = new ObjectMapper();
        FeatureCollection fc = objectMapper.readValue(body, FeatureCollection.class);


        List<Coordinate> coordinates = new ArrayList<>();

        List<Feature>  features   =  fc.getFeatures();

        features.forEach(feature -> coordinates.addAll(feature.getGeometry().getCoordinates()));

        Map<String, BigDecimal> placeDistance = apiRoutesService.calculateDistance(coordinates);
        List<String> userPreferredPlaces = List.of("삼청각", "춘천손칼국수", "사프란");
        List<String> planPreferredPlaces = List.of("명동교자", "지대방", "사유");
        String additionalCondition = "남해 바다가 인접해 있으면 좋겠어요.";

        List<String> stringList = aiRecommendService.requestRecommendPlaces(placeDistance, userPreferredPlaces,
                planPreferredPlaces, additionalCondition);

        System.out.println(stringList.toString());
    }
}
