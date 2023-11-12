package com.gdsc2023.planyee.domain.plan.controller;


import com.gdsc2023.planyee.domain.plan.dto.UserPlanDescriptionDto;
import com.gdsc2023.planyee.domain.plan.dto.UserPlanRequestDto;
import com.gdsc2023.planyee.domain.plan.repository.PlanRepository;
import com.gdsc2023.planyee.domain.plan.service.PlanService;
import com.gdsc2023.planyee.domain.tmap.controller.routesApiController;
import com.gdsc2023.planyee.domain.tmap.domain.ApiRequestParam;
import com.gdsc2023.planyee.domain.tmap.dto.UserRouteDto;
import com.gdsc2023.planyee.global.config.oauth.LoginUser;
import com.gdsc2023.planyee.global.config.oauth.SessionUser;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PlanController {

    private final PlanService planService;
    private final routesApiController routesApiController;

    @PostMapping("/main")
    public Long createPlanBy(@LoginUser SessionUser user, UserPlanRequestDto request) {
        ApiRequestParam placeRequests = ApiRequestParam.builder()
                                               .endX(request.getDestinationLatitude())
                                               .endY(request.getDestinationLongitude())
                                               .startX(request.getSourceLatitude())
                                               .startY(request.getSourceLongitude())
                                               .build();
        UserPlanDescriptionDto userDescription = UserPlanDescriptionDto.builder()
                .planPreferedPlaces(request.getPlanPreferedPlaces())
                .additionalCondition(request.getAdditionalCondition())
                .build();
        UserRouteDto userRouteDto = routesApiController.calculateMilestones(user, placeRequests, userDescription);
        return planService.createPlanBy(user, request, userRouteDto);
    }


}
