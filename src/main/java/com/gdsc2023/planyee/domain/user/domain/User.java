package com.gdsc2023.planyee.domain.user.domain;

import jakarta.persistence.*;

import com.gdsc2023.planyee.domain.common.BaseEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Table(name = "users")
@Entity
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, updatable = false)
    private Long id;

    @Column(unique = true, nullable = false)
    private String oauthId;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    @Column(nullable = false)
    private Integer age;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Role role;

    @Builder
    private User(String oauthId, String nickname, Gender gender, Integer age, Role role) {
        this.oauthId = oauthId;
        this.nickname = nickname;
        this.gender = gender;
        this.age = age;
        this.role = role;
    }
}

