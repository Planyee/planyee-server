package com.gdsc2023.planyee.global.config.oauth;

import java.io.Serializable;
import com.gdsc2023.planyee.global.util.DateUtil;
import com.gdsc2023.planyee.domain.user.domain.Gender;
import com.gdsc2023.planyee.domain.user.domain.User;

import lombok.Getter;

@Getter
public class SessionUser implements Serializable {
    private Long id;
    private String oauthId;
    private String nickname;
    private Gender gender;
    private Integer age;

    public SessionUser(User user) {
        this.id = user.getId();
        this.oauthId = user.getOauthId();
        this.nickname = user.getNickname();
        this.gender = user.getGender();
        this.age = DateUtil.getAgeByBirthyear(user.getBirthYear());
    }
}
