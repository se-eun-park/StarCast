package com.mobyeoldol.starcast.place.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum PlaceType {
    EUP_MYUN_DONG("읍면동"),
    OBSERVATORY("천문대");

    private final String koreanName;
}
