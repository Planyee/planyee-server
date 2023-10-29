package com.gdsc2023.planyee.tmap;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gdsc2023.planyee.domain.tmap.domain.TmapApiRequestParam;
import com.gdsc2023.planyee.domain.tmap.domain.TmapApiResponseParam.Coordinate;
import com.gdsc2023.planyee.domain.tmap.domain.TmapApiResponseParam.Feature;
import com.gdsc2023.planyee.domain.tmap.domain.TmapApiResponseParam.FeatureCollection;
import com.gdsc2023.planyee.domain.tmap.domain.TmapApiResponseParam.Geometry;
import com.gdsc2023.planyee.domain.tmap.service.TmapApiRoutesService;
import com.mysql.cj.xdevapi.JsonString;
import com.nimbusds.jose.shaded.gson.JsonElement;
import com.nimbusds.jose.shaded.gson.JsonObject;
import io.swagger.v3.core.util.Json;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.client.MockRestServiceServer;


@RestClientTest(value = {TmapApiRoutesService.class})
@MockBean(JpaMetamodelMappingContext.class)
public class TmapApiRequestTest {


    @Autowired
    TmapApiRoutesService apiRoutesService;

    @Autowired
    private MockRestServiceServer mockServer;

    @Test
    @DisplayName("자동차 경로 api의 반환값 확인")
    void carRequestTest() throws Exception {

        TmapApiRequestParam requestParam = TmapApiRequestParam.builder()
                .endX(new BigDecimal("129.07579349764512"))
                .endY(new BigDecimal("35.17883196265564"))
                .startX(new BigDecimal("126.98217734415019"))
                .startY(new BigDecimal("37.56468648536046"))
                .build();


        ResponseEntity<String> entity = apiRoutesService.requestCarRoutes(requestParam);

        String body = entity.getBody();

        ObjectMapper objectMapper = new ObjectMapper();

        FeatureCollection fc = objectMapper.readValue(body, FeatureCollection.class);

        List<Coordinate> coordinates = fc.getFeatures()
                                        .stream()
                                        .map(Feature::getGeometry)
                                        .flatMap(geometry -> geometry.getCoordinates().stream())
                                         .toList();

        System.out.println("coordinates = " + coordinates);


    }
}
