package com.mobyeoldol.starcast.auth.application.service;

import com.mobyeoldol.starcast.auth.application.dto.KakaoLogoutResponseDto;

public interface AuthService {

    String getAccessCode();
    String getAccessToken(String code);
    KakaoLogoutResponseDto logout(String accessToken);
}
