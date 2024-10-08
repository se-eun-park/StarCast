package com.mobyeoldol.starcast.member.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum CastarImage {
    BASIC_1("베이직 이미지 1"),
    BASIC_2("베이직 이미지 2"),
    BASIC_3("베이직 이미지 3"),
    BRONZE_1("브론즈 이미지 1"),
    BRONZE_2("브론즈 이미지 2"),
    SILVER_1("실버 이미지 1"),
    SILVER_2("실버 이미지 2"),
    SILVER_3("실버 이미지 3"),
    GOLD_1("골드 이미지 1"),
    GOLD_2("골드 이미지 2"),
    GOLD_3("골드 이미지 3"),
    PLATINUM_1("플래티넘 이미지 1"),
    PLATINUM_2("플래티넘 이미지 2");

    private final String koreanName;
}
