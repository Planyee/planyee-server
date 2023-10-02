package com.gdsc2023.planyee.domain.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gdsc2023.planyee.domain.user.domain.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByOauthId(String oauthId);
}
