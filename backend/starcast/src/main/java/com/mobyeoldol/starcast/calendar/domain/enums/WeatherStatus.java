package com.mobyeoldol.starcast.calendar.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum WeatherStatus {
    CLEAR(1,"맑음"),
    PARTLY_CLOUD(2,"구름 많음"),
    CLOUDY(3,"흐림");

    private final int code;
    private final String koreanName;
}
