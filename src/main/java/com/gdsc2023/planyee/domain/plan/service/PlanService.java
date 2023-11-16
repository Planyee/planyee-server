package com.gdsc2023.planyee.domain.plan.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.gdsc2023.planyee.domain.ai.service.AiRecommendService;
import com.gdsc2023.planyee.domain.category.domain.Category;
import com.gdsc2023.planyee.domain.place.domain.Place;
import com.gdsc2023.planyee.domain.place.service.PlaceService;
import com.gdsc2023.planyee.domain.plan.dto.PlanCreateRequest;
import com.gdsc2023.planyee.domain.tmap.domain.TmapRoutesRequest;
import com.gdsc2023.planyee.domain.tmap.domain.TmapRoutesResponse;
import com.gdsc2023.planyee.domain.tmap.service.RoutesApiService;
import com.gdsc2023.planyee.domain.tmap.util.TmapRoutesResponseParser;
import com.gdsc2023.planyee.domain.user.domain.User;
import com.gdsc2023.planyee.domain.user.service.UserService;
import com.gdsc2023.planyee.domain.place.dto.Coordinate;
import com.gdsc2023.planyee.domain.place.dto.PlaceDto;
import com.gdsc2023.planyee.domain.plan.domain.Plan;
import com.gdsc2023.planyee.domain.plan.dto.PlanDetail;
import com.gdsc2023.planyee.domain.plan.dto.PlanSummary;
import com.gdsc2023.planyee.domain.plan.repository.PlanRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class PlanService {
    private final RoutesApiService routesApiService;
    private final AiRecommendService aiRecommendService;
    private final UserService userService;
    private final PlaceService placeService;
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
        Coordinate destinationCoordinate = new Coordinate(plan.getDestinationLatitude(),
                plan.getDestinationLongitude());
        List<PlaceDto> placeDtos = new ArrayList<>();

        for (Place place : placeList) {
            List<String> mainCategories = place.getCategoryList().stream().map(Category::getMainCategory).toList();
            List<String> subCategories = place.getCategoryList().stream().map(Category::getName).toList();
            placeDtos.add(PlaceDto.create(place, mainCategories, subCategories));
        }

        return new PlanDetail(sourceCoordinate, destinationCoordinate, placeDtos);
    }

    @Transactional
    public Plan createPlan(Long userId, PlanCreateRequest request) {
        TmapRoutesRequest tmapRoutesRequest = TmapRoutesRequest.create(request);
        List<String> planPreferredPlaces = request.getPlanPreferredPlaces();
        String additionalCondition = request.getAdditionalCondition();

        List<Place> recommendPlaces =
                getRecommendPlaces(userId, tmapRoutesRequest, planPreferredPlaces, additionalCondition);
        User user = userService.getUser(userId);

        Plan plan = Plan.toEntity(user, request, recommendPlaces);
        planRepository.save(plan);

        return plan;
    }

    private List<Place> getRecommendPlaces(Long userId, TmapRoutesRequest tmapRoutesRequest,
                                            List<String> planPreferredPlaces, String additionalCondition) {
        try {
            ResponseEntity<String> routes = routesApiService.requestRoutes(tmapRoutesRequest);

            List<TmapRoutesResponse.Coordinate> coordinates = TmapRoutesResponseParser.ParseToCoordinates(routes);
            Map<String, BigDecimal> placeDistances = routesApiService.calculateDistance(coordinates);

            List<String> userPreferredPlaces = placeService.findUserPrefferedPlaces(userId)
                    .stream().map(Place::getName).toList();

            List<String> placeNames = aiRecommendService.requestRecommendPlaces(
                    placeDistances, userPreferredPlaces, planPreferredPlaces, additionalCondition);

            return placeService.findPlacesWithName(placeNames);
        } catch (JsonProcessingException e) {
            throw new RuntimeException();
        }
    }
}
