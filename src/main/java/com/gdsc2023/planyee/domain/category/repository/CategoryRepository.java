package com.gdsc2023.planyee.domain.category.repository;

import com.gdsc2023.planyee.domain.category.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
