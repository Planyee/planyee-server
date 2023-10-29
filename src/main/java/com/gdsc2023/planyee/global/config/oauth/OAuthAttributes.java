package com.gdsc2023.planyee.global.config.oauth;

import java.util.Map;

import com.gdsc2023.planyee.domain.user.domain.Gender;
import com.gdsc2023.planyee.domain.user.domain.Role;
import com.gdsc2023.planyee.domain.user.domain.User;
import com.gdsc2023.planyee.global.util.DateUtil;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class OAuthAttributes {
    private final Map<String, Object> attributes;
    private final String nameAttributeKey;
    private final String oauthId;
    private final String nickname;
    private final String gender;
    private final Integer birthyear;

    public static OAuthAttributes of(String registrationId, Map<String, Object> attributes) {
        return ofNaver("id", attributes);
    }

    private static OAuthAttributes ofNaver(String userNameAttributeName, Map<String, Object> attributes) {
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");

        return OAuthAttributes.builder()
                .oauthId((String) response.get("id"))
                .nickname((String) response.get("nickname"))
                .gender((String) response.get("gender"))
                .birthyear(Integer.parseInt((String) response.get("birthyear")))
                .attributes(response)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    public User toEntity() {
        return User.builder()
                .oauthId(oauthId)
                .nickname(nickname)
                .gender(Gender.fromKey(gender))
                .role(Role.USER)
                .build();
    }

    private Integer toAge(Integer birthyear) {
        return DateUtil.getCurrentYear() - birthyear + 1;
    }
}