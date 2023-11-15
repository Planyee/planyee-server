package com.gdsc2023.planyee.domain.plan.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import com.gdsc2023.planyee.domain.plan.domain.Plan;
import com.gdsc2023.planyee.domain.plan.dto.PlanDetail;
import com.gdsc2023.planyee.domain.plan.dto.PlanSummary;
import com.gdsc2023.planyee.domain.plan.dto.PlanCreateRequest;
import com.gdsc2023.planyee.domain.plan.service.PlanService;
import com.gdsc2023.planyee.global.config.oauth.LoginUser;
import com.gdsc2023.planyee.global.config.oauth.SessionUser;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class PlanController {
    private final PlanService planService;

    @GetMapping("/main")
    public List<PlanSummary> getPlanSummarys() {
        //        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        return planService.getPlanSummarys(3L);
    }

    @GetMapping("/list/{planId}")
    public PlanDetail getPlanDetail(@PathVariable Long planId) {
        return planService.getPlanDetail(planId);
    }

    @PostMapping("/main")
    public Long createPlan(PlanCreateRequest request) {
        //        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        System.out.println(request.getSourceLatitude());
        System.out.println(request.getSourceLongitude());
        System.out.println(request.getDestinationLatitude());
        System.out.println(request.getDestinationLongitude());
        Plan plan = planService.createPlan(3L, request);
        return plan.getId();
    }
}
