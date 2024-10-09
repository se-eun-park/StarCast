package com.mobyeoldol.starcast.auth.application.service;


import com.mobyeoldol.starcast.auth.application.dto.KakaoTokenInfoResponseDto;
import com.mobyeoldol.starcast.auth.application.dto.KakaoTokenResponseDto;
import com.mobyeoldol.starcast.auth.application.dto.KakaoUserInfoResponseDto;
import com.mobyeoldol.starcast.auth.domain.Auth;
import com.mobyeoldol.starcast.auth.domain.UserInfoTmp;
import com.mobyeoldol.starcast.auth.presentation.response.UpdateUserInfoTmpResponse;

public interface AuthService {

    String getAccessCode();
    KakaoTokenResponseDto getAccessToken(String code);
    KakaoUserInfoResponseDto checkUserInfo(String accessToken);
    void generateAuthAndUserInfoTmp(KakaoUserInfoResponseDto dto);
    KakaoTokenInfoResponseDto getKakaoId(String accessToken, String refreshToken);
    UpdateUserInfoTmpResponse updateUserInfoTmp(Auth kakaoUid, boolean consentGps, boolean consentNotice);

    String  authenticateMember(String accessToken); //token to kakaoId
}
