package com.mobyeoldol.starcast.calendar.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum PrecipitationStatus {
    NONE(0, "없음"),
    RAIN(1,"비"),
    RAIN_AND_SNOW(2,"비/눈"),
    SNOW(3,"눈"),
    SHOWER(4,"소나기");

    private final int code;
    private final String koreanName;
}
