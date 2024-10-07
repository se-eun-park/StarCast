package com.mobyeoldol.starcast.auth.application.service;


import com.mobyeoldol.starcast.auth.application.dto.KakaoTokenResponseDto;
import com.mobyeoldol.starcast.auth.application.dto.KakaoUserInfoResponseDto;
import jakarta.servlet.http.HttpServletResponse;

public interface AuthService {

    String getAccessCode();
    KakaoTokenResponseDto getAccessToken(String code);
    KakaoUserInfoResponseDto checkUserInfo(String accessToken);
    void generateAuthAndUserInfoTmp(KakaoUserInfoResponseDto dto);
//    Long authenticateMember(String accessToken); //token to kakaoId
//    String kakaoUidToProfileUid(Long kakaoUid); //kakaoId to profileUid
    String  authenticateMember(String accessToken); //token to kakaoId
}
