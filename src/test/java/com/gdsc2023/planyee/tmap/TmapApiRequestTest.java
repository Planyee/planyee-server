package com.gdsc2023.planyee.tmap;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.gdsc2023.planyee.domain.tmap.domain.apiRequestParam;
import com.gdsc2023.planyee.domain.tmap.domain.apiResponseParam.Coordinate;
import com.gdsc2023.planyee.domain.tmap.domain.apiResponseParam.Feature;
import com.gdsc2023.planyee.domain.tmap.domain.apiResponseParam.FeatureCollection;
import com.gdsc2023.planyee.domain.tmap.service.routesApiService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.util.StopWatch;


@RestClientTest(value = {routesApiService.class})
@MockBean(JpaMetamodelMappingContext.class)
public class TmapApiRequestTest {


    @Autowired
    routesApiService apiRoutesService;

    @Autowired
    private MockRestServiceServer mockServer;

    @Test
    @DisplayName("자동차 경로 api의 반환값 확인")
    void carRequestTest() throws Exception {

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        apiRequestParam requestParam = apiRequestParam.builder()
                                                      .endX(new BigDecimal("126.9235355222221"))
                                                      .endY(new BigDecimal("37.460122514035135"))
                                                      .startX(new BigDecimal("126.88462351538156"))
                                                      .startY(new BigDecimal("37.47955275185523"))
                                                      .build();

        ResponseEntity<String> entity = apiRoutesService.requestCarRoutes(requestParam);
        stopWatch.stop();

        String body = entity.getBody();

        ObjectMapper objectMapper = new ObjectMapper();
        FeatureCollection fc = objectMapper.readValue(body, FeatureCollection.class);


        List<Coordinate> coordinates = new ArrayList<>();

        List<Feature>  features   =  fc.getFeatures();

        features.forEach(feature -> coordinates.addAll(feature.getGeometry().getCoordinates()));

        System.out.println(stopWatch.prettyPrint());



    }
}
