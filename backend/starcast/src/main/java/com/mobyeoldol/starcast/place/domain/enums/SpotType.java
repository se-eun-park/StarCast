package com.mobyeoldol.starcast.place.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum SpotType {
    FAVOURITE("즐겨찾기"),
    MY_PLACE("내 장소"),
    PLAN("찜");

    private final String koreanName;
}
