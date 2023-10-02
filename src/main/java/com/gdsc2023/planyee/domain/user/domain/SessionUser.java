package com.gdsc2023.planyee.domain.user.domain;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    private String oauthId;
    private String nickname;
    private Gender gender;
    private Integer age;

    public SessionUser(User user) {
        this.oauthId = user.getOauthId();
        this.nickname = user.getNickname();
        this.gender = user.getGender();
        this.age = user.getAge();
    }
}
