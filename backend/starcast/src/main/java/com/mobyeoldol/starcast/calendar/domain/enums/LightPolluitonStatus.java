package com.mobyeoldol.starcast.calendar.domain.enums;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum LightPolluitonStatus {
    LOW(1, "낮은 광공해"),
    MEDIUM(2, "보통 광공해"),
    HIGH(3, "높은 광공해");

    private final int code;
    private final String koreanName;
}
