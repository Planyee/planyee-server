package com.gdsc2023.planyee.domain.plan.repository;

import com.gdsc2023.planyee.domain.plan.domain.Plan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanRepository extends JpaRepository<Plan, Long> {


}
