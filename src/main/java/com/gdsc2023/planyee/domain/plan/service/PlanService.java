package com.gdsc2023.planyee.domain.plan.service;

import com.gdsc2023.planyee.domain.category.domain.Category;
import com.gdsc2023.planyee.domain.place.domain.Place;
import com.gdsc2023.planyee.domain.place.dto.Coordinate;
import com.gdsc2023.planyee.domain.place.dto.PlaceDto;
import com.gdsc2023.planyee.domain.plan.domain.Plan;
import com.gdsc2023.planyee.domain.plan.dto.PlanDetail;
import com.gdsc2023.planyee.domain.plan.dto.PlanSummary;
import com.gdsc2023.planyee.domain.plan.dto.UserPlanRequestDto;
import com.gdsc2023.planyee.domain.plan.repository.PlanRepository;
import com.gdsc2023.planyee.domain.user.repository.UserRepository;
import com.gdsc2023.planyee.global.config.oauth.SessionUser;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class PlanService {
    private final PlanRepository planRepository;

    public List<PlanSummary> getPlanSummarys(Long userId) {
        List<Plan> userPlans = planRepository.findAllByUserId(userId);

        List<PlanSummary> userPlanSummarys = userPlans.stream()
                .map(plan -> new PlanSummary(plan.getId(), plan.getName(), plan.getDate()))
                .toList();

        return userPlanSummarys;
    }

    public PlanDetail getPlanDetail(Long planId) {
        Plan plan = planRepository.findById(planId).get();
        List<Place> placeList = plan.getPlaceList();

        Coordinate sourceCoordinate = new Coordinate(plan.getSourceLatitude(), plan.getSourceLongitude());
        Coordinate destinationCoordinate = new Coordinate(plan.getDestinationLatitude(), plan.getDestinationLongitude());
        List<PlaceDto> placeDtos = new ArrayList<>();

        for (Place place : placeList) {
            List<String> mainCategories = place.getCategoryList().stream().map(Category::getMainCategory).toList();
            List<String> subCategories = place.getCategoryList().stream().map(Category::getName).toList();

            placeDtos.add(new PlaceDto(
                    place.getName(),
                    new HashSet<>(mainCategories),
                    new HashSet<>(subCategories),
                    place.getAddress(),
                    place.getDescription(),
                    place.getEtc()
            ));
        }

        return new PlanDetail(sourceCoordinate, destinationCoordinate, placeDtos);
    }

    private final UserRepository userRepository;
    public Long createPlanBy(SessionUser sessionUser, UserPlanRequestDto request, List<Place> mileStones) {
        Plan plan = Plan.builder()
                        .name(request.getPlanName())
                        .destinationLatitude(request.getDestinationLatitude())
                        .destinationLongitude(request.getDestinationLongitude())
                        .sourceLatitude(request.getSourceLatitude())
                        .sourceLongitude(request.getSourceLongitude())
                        .user(userRepository.findByOauthId(sessionUser.getOauthId())
                                            .get())
                        .placeList(mileStones)
                        .date(request.getDate())
                        .additionalDescription(request.getAdditionalCondition())
                        .build();
        Plan savedPlan = planRepository.save(plan);

        return savedPlan.getId();
    }

}
