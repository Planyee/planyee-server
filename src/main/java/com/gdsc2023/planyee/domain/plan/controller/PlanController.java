package com.gdsc2023.planyee.domain.plan.controller;

import com.gdsc2023.planyee.domain.place.domain.Place;
import com.gdsc2023.planyee.domain.plan.dto.PlanDetail;
import com.gdsc2023.planyee.domain.plan.dto.PlanSummary;
import com.gdsc2023.planyee.domain.plan.dto.UserPlanDescriptionDto;
import com.gdsc2023.planyee.domain.plan.dto.UserPlanRequestDto;
import com.gdsc2023.planyee.domain.plan.service.PlanService;
import com.gdsc2023.planyee.domain.tmap.controller.routesApiController;
import com.gdsc2023.planyee.domain.tmap.domain.ApiRequestParam;
import com.gdsc2023.planyee.global.config.oauth.LoginUser;
import com.gdsc2023.planyee.global.config.oauth.SessionUser;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class PlanController {
    private final PlanService planService;
    private final routesApiController routesApiController;
    @GetMapping("/main")
    public List<PlanSummary> getPlanSummarys(@LoginUser SessionUser user) {
        return planService.getPlanSummarys(user.getId());
    }

    @GetMapping("/list/{planId}")
    public PlanDetail getPlanDetail(@PathVariable Long planId) {
        return planService.getPlanDetail(planId);

    }
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

        List<Place> milestones = routesApiController.calculateMilestones(user, placeRequests, userDescription);
        return planService.createPlanBy(user, request, milestones);
    }

}
