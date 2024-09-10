package com.mobyeoldol.starcast.auth.application.service;

import com.mobyeoldol.starcast.auth.application.dto.KakaoAuthenticateUserResponseDto;
import com.mobyeoldol.starcast.auth.application.dto.KakaoLogoutResponseDto;
import com.mobyeoldol.starcast.auth.application.dto.KakaoUnlinkResponseDto;
import com.mobyeoldol.starcast.auth.application.dto.KakaoUserInfoResponseDto;
import jakarta.transaction.Transactional;

import java.util.UUID;

public interface AuthService {

    String getAccessCode();

    String getAccessToken(String code);

    KakaoLogoutResponseDto logout(String accessToken);

    KakaoAuthenticateUserResponseDto authenticateUser(String accessToken);

    KakaoUnlinkResponseDto unlink(String accessToken);

    @Transactional
    KakaoUserInfoResponseDto getUserInfo(String accessToken);


    UUID generateType4UUID();

//    String logoutWithKakaoAccount();
}
