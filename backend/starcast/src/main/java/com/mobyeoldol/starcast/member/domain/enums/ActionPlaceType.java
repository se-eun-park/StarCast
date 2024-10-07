package com.mobyeoldol.starcast.member.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ActionPlaceType {
    GPS("GPS"),
    MY_SPOT("내 장소");

    private final String koreanName;
}
