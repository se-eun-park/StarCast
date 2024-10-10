package com.mobyeoldol.starcast.community.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum SortType {
    REACTION("인기"),
    NEWEST("최신");

    private final String koreanName;
}
