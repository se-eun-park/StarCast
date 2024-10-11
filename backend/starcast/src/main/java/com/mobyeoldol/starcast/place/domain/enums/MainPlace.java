package com.mobyeoldol.starcast.place.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum MainPlace{
    GPS("GPS"),
    MY_SPOT("내 장소");

    private final String koreanName;
}