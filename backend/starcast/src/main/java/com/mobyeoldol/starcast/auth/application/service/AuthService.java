package com.mobyeoldol.starcast.auth.application.service;


import com.mobyeoldol.starcast.auth.application.dto.KakaoTokenInfoResponseDto;
import com.mobyeoldol.starcast.auth.domain.Auth;
import com.mobyeoldol.starcast.auth.presentation.response.LogoutResponse;
import com.mobyeoldol.starcast.auth.presentation.response.UnlinkResponse;
import com.mobyeoldol.starcast.auth.application.dto.KakaoTokenResponseDto;
import com.mobyeoldol.starcast.auth.application.dto.KakaoUserInfoResponseDto;
import com.mobyeoldol.starcast.auth.presentation.response.UpdateUserInfoTmpResponse;

public interface AuthService {

    String getAccessCode();
    KakaoTokenResponseDto getAccessToken(String code);
    KakaoUserInfoResponseDto checkUserInfo(String accessToken);
    void generateAuthAndUserInfoTmp(KakaoUserInfoResponseDto dto);
    KakaoTokenInfoResponseDto getKakaoId(String accessToken, String refreshToken);
    UpdateUserInfoTmpResponse updateUserInfoTmp(Auth kakaoUid, boolean consentGps, boolean consentNotice);
    String authenticateMember(String bearerToken);
    KakaoTokenInfoResponseDto getTokenInfo(String bearerToken);
    Auth tokenInfoToAuth(KakaoTokenInfoResponseDto dto);
    String authToProfileUid(Auth auth);
    LogoutResponse logout(String bearerToken);
    UnlinkResponse unlink(String bearerToken);
    void generateProfile(String nickname, String bearerToken);
}
