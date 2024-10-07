package com.mobyeoldol.starcast.calendar.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum MoonStatus {
    DARK_MOON(1, "어두운 달"),
    MOON(2, "달"),
    BRIGHT_MOON(3, "밝은 달");

    private final int code;
    private final String koreanName;
}