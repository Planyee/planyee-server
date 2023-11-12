package com.gdsc2023.planyee.domain.plan.service;

import com.gdsc2023.planyee.domain.plan.domain.Plan;
import com.gdsc2023.planyee.domain.plan.dto.UserPlanRequestDto;
import com.gdsc2023.planyee.domain.plan.repository.PlanRepository;
import com.gdsc2023.planyee.domain.tmap.dto.UserRouteDto;
import com.gdsc2023.planyee.domain.user.repository.UserRepository;
import com.gdsc2023.planyee.global.config.oauth.LoginUser;
import com.gdsc2023.planyee.global.config.oauth.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PlanService {


    private final PlanRepository planRepository;
    private final UserRepository userRepository;
    public Long createPlanBy(SessionUser sessionUser, UserPlanRequestDto request, ) {
        Plan plan = Plan.builder()
                .name(request.getPlanName())
                .destinationLatitude(request.getDestinationLatitude())
                .destinationLongitude(request.getDestinationLongitude())
                .sourceLatitude(request.getSourceLatitude())
                .sourceLongitude(request.getSourceLongitude())
                .user(userRepository.findByOauthId(sessionUser.getOauthId()).get())
                .date(request.getDate())
                .placeList(userRouteDto.getMileStones())
                .additionalDescription(request.getAdditionalCondition())
                .build();
        Plan savedPlan = planRepository.save(plan);

        return savedPlan.getId();
    }
}
