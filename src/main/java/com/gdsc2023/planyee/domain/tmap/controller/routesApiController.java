package com.gdsc2023.planyee.domain.tmap.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gdsc2023.planyee.domain.tmap.domain.ApiRequestParam;
import com.gdsc2023.planyee.domain.tmap.domain.apiResponseParam.Coordinate;
import com.gdsc2023.planyee.domain.tmap.dto.AiResponseParam;
import com.gdsc2023.planyee.domain.tmap.dto.EndPoint;
import com.gdsc2023.planyee.domain.tmap.dto.MileStone;
import com.gdsc2023.planyee.domain.tmap.dto.PlaceDistanceDto;
import com.gdsc2023.planyee.domain.tmap.dto.StartPoint;
import com.gdsc2023.planyee.domain.tmap.dto.UserRouteDto;
import com.gdsc2023.planyee.domain.tmap.service.AiRequestService;
import com.gdsc2023.planyee.domain.tmap.service.MileStoneDtoFactoryService;
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
    @Autowired
    AiRequestService aiRequestService;
    @Autowired
    MileStoneDtoFactoryService mileStoneDtoFactoryService;

    public UserRouteDto calculateMilestones(@RequestBody ApiRequestParam requestParam) {

        ResponseEntity<String> reponses = routesApiService.requestCarRoutes(requestParam);
        try {
            List<Coordinate> coordinates = ApiResponseParserUtil.ParseToCoordinates(reponses);
            List<PlaceDistanceDto> placeDistances = routesApiService.calculateDistanceWith(
                    coordinates);
            List<MileStone> mileStones = requestMilestoneToAi(placeDistances);
            return new UserRouteDto(new StartPoint(requestParam.getStartX(), requestParam.getStartY()), mileStones, new EndPoint(requestParam.getEndX(), requestParam.getEndY()));

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }


    public List<MileStone> requestMilestoneToAi(List<PlaceDistanceDto> placeDistances) {
        // place id들
        ResponseEntity<String> milestones = aiRequestService.requestMileStonesWith(placeDistances);

        String body = milestones.getBody();

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            AiResponseParam response = objectMapper.readValue(body, AiResponseParam.class);
            List<Long> placeIds = response.getPlaceIds();
            List<MileStone> mileStones = mileStoneDtoFactoryService.createMileStonesWith(placeIds);
            return mileStones;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }








}
