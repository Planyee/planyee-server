package com.gdsc2023.planyee.domain.plan.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.gdsc2023.planyee.domain.plan.domain.Plan;

public interface PlanRepository extends JpaRepository<Plan, Long> {
    List<Plan> findAllByUserId(Long userId);
}
