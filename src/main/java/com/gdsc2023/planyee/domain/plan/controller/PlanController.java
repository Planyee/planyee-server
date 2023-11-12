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
    public List<PlanSummary> getPlanSummarys(@LoginUser SessionUser user) {
        return planService.getPlanSummarys(user.getId());
    }

    @GetMapping("/list/{planId}")
    public PlanDetail getPlanDetail(@PathVariable Long planId) {
        return planService.getPlanDetail(planId);
    }

    @PostMapping("/main")
    public Long createPlan(@LoginUser SessionUser user, PlanCreateRequest request) {
        Plan plan = planService.createPlan(user.getId(), request);
        return plan.getId();
    }
}
