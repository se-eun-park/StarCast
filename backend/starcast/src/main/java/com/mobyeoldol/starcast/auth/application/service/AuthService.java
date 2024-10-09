package com.mobyeoldol.starcast.auth.application.service;


import com.mobyeoldol.starcast.auth.application.dto.KakaoTokenInfoResponseDto;
import com.mobyeoldol.starcast.auth.domain.Auth;
import com.mobyeoldol.starcast.auth.presentation.response.LogoutResponse;
import com.mobyeoldol.starcast.auth.presentation.response.UnlinkResponse;

public interface AuthService {


    String authenticateMember(String bearerToken);
    KakaoTokenInfoResponseDto getTokenInfo(String bearerToken);
    Auth tokenInfoToAuth(KakaoTokenInfoResponseDto dto);
    String authToProfileUid(Auth auth);
    LogoutResponse logout(String bearerToken);
    UnlinkResponse unlink(String bearerToken);
    void generateProfile(String nickname, String bearerToken);
}
