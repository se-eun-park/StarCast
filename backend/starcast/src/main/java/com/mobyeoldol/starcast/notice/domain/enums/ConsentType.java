package com.mobyeoldol.starcast.notice.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ConsentType {
    GPS("GPS"),
    NOTICE("알림");

    private final String koreanName;
}
