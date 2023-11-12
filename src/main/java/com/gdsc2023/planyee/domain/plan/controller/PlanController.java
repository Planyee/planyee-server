package com.gdsc2023.planyee.domain.plan.controller;

import com.gdsc2023.planyee.domain.plan.dto.PlanDetail;
import com.gdsc2023.planyee.domain.plan.dto.PlanSummary;
import com.gdsc2023.planyee.domain.plan.service.PlanService;
import com.gdsc2023.planyee.global.config.oauth.LoginUser;
import com.gdsc2023.planyee.global.config.oauth.SessionUser;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
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
}
