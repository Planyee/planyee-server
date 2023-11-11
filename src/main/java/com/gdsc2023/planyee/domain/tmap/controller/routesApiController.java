package com.gdsc2023.planyee.domain.tmap.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.gdsc2023.planyee.domain.tmap.domain.ApiRequestParam;
import com.gdsc2023.planyee.domain.tmap.domain.apiResponseParam.Coordinate;
import com.gdsc2023.planyee.domain.tmap.dto.PlaceDistanceDto;
import com.gdsc2023.planyee.domain.tmap.service.routesApiService;
import com.gdsc2023.planyee.domain.tmap.util.ApiResponseParserUtil;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class routesApiController {

    @Autowired
    routesApiService routesApiService;

    public List<PlaceDistanceDto> calculatePlaceDistance(@RequestBody ApiRequestParam requestParam) {

        ResponseEntity<String> reponses = routesApiService.requestCarRoutes(requestParam);
        try {
            List<Coordinate> coordinates = ApiResponseParserUtil.ParseToCoordinates(reponses);
            return routesApiService.calculateDistanceWith(
                    coordinates);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }


}
