package com.gdsc2023.planyee.domain.user.service;

import java.util.Collections;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import com.gdsc2023.planyee.domain.user.domain.User;
import com.gdsc2023.planyee.domain.user.repository.UserRepository;
import com.gdsc2023.planyee.global.config.oauth.OAuthAttributes;
import com.gdsc2023.planyee.global.config.oauth.SessionUser;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final UserRepository userRepository;
    private final HttpSession httpSession;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        // naver, kakao, google 등의 서비스를 구분
        String registrationId = userRequest.getClientRegistration().getRegistrationId();

        // 각 서비스별 attributes 통일
        OAuthAttributes attributes = OAuthAttributes.of(registrationId, oAuth2User.getAttributes());

        // DB에 유저정보 업데이트 & 세션 저장
        User user = save(attributes);
        httpSession.setAttribute("user", new SessionUser(user));
        System.out.println("===============" + httpSession.getId() + "===============");

        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority(user.getRole().getKey())),
                attributes.getAttributes(),
                attributes.getNameAttributeKey());
    }

    private User save(OAuthAttributes attributes) {
        User user = userRepository.findByOauthId(attributes.getOauthId())
                .orElse(attributes.toEntity());

        return userRepository.save(user);
    }
}
