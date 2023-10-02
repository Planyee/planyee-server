package com.gdsc2023.planyee.domain.user.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Gender {
    MALE("M"), FEMALE("F");

    private final String key;

    public static Gender fromKey(String key) {
        if(key.equals(MALE.getKey())) {
            return MALE;
        } else {
            return FEMALE;
        }
    }
}
