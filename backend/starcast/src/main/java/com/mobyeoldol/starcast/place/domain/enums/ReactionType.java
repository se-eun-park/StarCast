package com.mobyeoldol.starcast.place.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ReactionType {
    VISIT_AGAIN("방문해볼래요"),
    HELPFUL("도움이 됐어요"),
    NICE_PHOTOS("사진이 예뻐요");

    private final String koreanName;
}
