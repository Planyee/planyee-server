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
//@RestClientTest(value = {routesApiService.class, })
//@MockBean(JpaMetamodelMappingContext.class)
public class TmapApiRequestTest {


    private static final BigDecimal EARTH_RADIUS = new BigDecimal("6371000"); // 지구의 반지름 (미터 단위)
    private static final int SCALE = 5; // BigDecimal 연산의 정밀도;

    @Autowired
    RoutesApiService apiRoutesService;
    AiRecommendService aiRecommendService;
//    @Autowired
    //private MockRestServiceServer mockServer;

    @Autowired
    PlaceRepository placeRepository;

    @Test
    @DisplayName("자동차 경로 api의 반환값 확인")
    void carRequestTest() throws Exception {

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        TmapRoutesRequest requestParam = TmapRoutesRequest.builder()
                                                      .endX(new BigDecimal("126.9235355222221"))
                                                      .endY(new BigDecimal("37.460122514035135"))
                                                      .startX(new BigDecimal("126.88462351538156"))
                                                      .startY(new BigDecimal("37.47955275185523"))
                                                      .build();

        ResponseEntity<String> entity = apiRoutesService.requestRoutes(requestParam);


        stopWatch.stop();

        String body = entity.getBody();

        ObjectMapper objectMapper = new ObjectMapper();
        FeatureCollection fc = objectMapper.readValue(body, FeatureCollection.class);


        List<Coordinate> coordinates = new ArrayList<>();

        List<Feature>  features   =  fc.getFeatures();

        features.forEach(feature -> coordinates.addAll(feature.getGeometry().getCoordinates()));

        Map<String, BigDecimal> placeDistance = apiRoutesService.calculateDistance(coordinates);
        List<String> userPreferredPlaces = List.of("삼청각", "춘천손칼국수", "사프란");
        List<String> planPreferredPlaces = List.of("명동교자", "지대방", "사유");
        String additionalCondition = "아무거나";

        List<String> stringList = aiRecommendService.requestRecommendPlaces(placeDistance, userPreferredPlaces,
                planPreferredPlaces, additionalCondition);

        System.out.println(stringList.toString());



    }

    private void tempMethod(List<Coordinate> coordinates) {


        List<Place> places = placeRepository.findAll();

        Map<Long, BigDecimal> result = new HashMap<>();
        List<PlaceDistance> placeDistanceList = new ArrayList<>();

        for (Place place : places) {
            Long placeId = place.getId();
            BigDecimal placeLatitude = place.getLatitude();
            BigDecimal placeLongitude = place.getLongitude();

            for (Coordinate coordinate : coordinates) {
                BigDecimal pointLatitude = coordinate.getLatitude();
                BigDecimal pointLongitude = coordinate.getLongitude();

                BigDecimal distance = calculateDistance(placeLatitude, placeLongitude, pointLongitude, pointLatitude);

                if(!result.containsKey(placeId)) {
                    result.put(placeId, distance);
                } else if(result.get(placeId).compareTo(distance) > 0) {
                    result.put(placeId, distance);
                }
            }

            placeDistanceList.add(new PlaceDistance(placeId, placeLatitude, placeLongitude, result.get(placeId)));
        }

        ObjectMapper mapper = new ObjectMapper();

        try {
            String json = mapper.writeValueAsString(placeDistanceList);
            System.out.println(json);
        } catch (Exception e) {
        }
    }

    public static BigDecimal calculateDistance(BigDecimal lat1, BigDecimal lon1, BigDecimal lat2, BigDecimal lon2) {
        BigDecimal latDistance = toRad(lat2.subtract(lat1));
        BigDecimal lonDistance = toRad(lon2.subtract(lon1));
        BigDecimal sinLat = sin(latDistance.divide(BigDecimal.valueOf(2)));
        BigDecimal sinLon = sin(lonDistance.divide(BigDecimal.valueOf(2)));
        BigDecimal a = sinLat.multiply(sinLat).add(cos(lat1).multiply(cos(lat2)).multiply(sinLon).multiply(sinLon));
        BigDecimal c = BigDecimal.valueOf(2).multiply(atan2(sqrt(a), sqrt(BigDecimal.ONE.subtract(a))));
        return EARTH_RADIUS.multiply(c).setScale(SCALE, RoundingMode.HALF_UP);
    }

    private static BigDecimal toRad(BigDecimal value) {
        return value.multiply(BigDecimal.valueOf(Math.PI / 180));
    }

    private static BigDecimal sin(BigDecimal value) {
        return BigDecimal.valueOf(Math.sin(value.doubleValue()));
    }

    private static BigDecimal cos(BigDecimal value) {
        return BigDecimal.valueOf(Math.cos(value.doubleValue()));
    }

    private static BigDecimal sqrt(BigDecimal value) {
        return BigDecimal.valueOf(Math.sqrt(value.doubleValue()));
    }

    private static BigDecimal atan2(BigDecimal y, BigDecimal x) {
        return BigDecimal.valueOf(Math.atan2(y.doubleValue(), x.doubleValue()));
    }

    static class PlaceDistance {
        public long id;
        public BigDecimal placeLongitude;
        public BigDecimal placeLatitude;
        public BigDecimal distance;




        public PlaceDistance(long id, BigDecimal placeLatitude, BigDecimal placeLongitude,  BigDecimal distance) {
            this.id = id;
            this.distance = distance;
        }
    }


}
