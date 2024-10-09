package com.mobyeoldol.starcast.auth.presentation;

import com.mobyeoldol.starcast.auth.application.dto.KakaoTokenResponseDto;
import com.mobyeoldol.starcast.auth.application.dto.KakaoUserInfoResponseDto;
import com.mobyeoldol.starcast.auth.application.service.AuthService;
import com.mobyeoldol.starcast.auth.domain.Auth;
import com.mobyeoldol.starcast.auth.domain.RefreshToken;
import com.mobyeoldol.starcast.auth.domain.repository.AuthRepository;
import com.mobyeoldol.starcast.auth.domain.repository.RefreshTokenRepository;
import com.mobyeoldol.starcast.auth.presentation.request.UpdateUserInfoTmpRequest;
import com.mobyeoldol.starcast.auth.presentation.response.UpdateUserInfoTmpResponse;
import com.mobyeoldol.starcast.global.template.BaseResponseTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Cookie;

import java.util.Optional;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Value("${kakao.redirect.uri.signup}")
    String kakaoRedirectUriSignup;

    @Value("${kakao.redirect.uri.home}")
    String kakaoRedirectUriHome;

    private final AuthService authService;
    private final AuthRepository authRepository;
    private final RefreshTokenRepository refreshTokenRepository;

    @GetMapping("/login")
    public RedirectView getAccessCode() {
        log.info("[login 유효 인가 코드 요청 API] GET /api/v1/auth/login");
        RedirectView response = new RedirectView();
        response.setUrl(authService.getAccessCode());

        log.info("[login 유효 인가 코드 요청 API] 인가 코드 받아서 리턴");
        return response;
    }

    @GetMapping("/redirect-login")
    public RedirectView getAccessToken(@RequestParam("code") String code, HttpServletResponse httpServletResponse) {
        log.info("[login 카카오에 토큰 요청 API] GET /api/v1/auth/redirect-login");
        KakaoTokenResponseDto responseDto = authService.getAccessToken(code);

        log.info("[login 카카오에 토큰 요청 API] Access Token 쿠키에 담기");
        Cookie accessTokenCookie = new Cookie("accessToken", responseDto.getAccessToken());
        accessTokenCookie.setHttpOnly(true); // JavaScript에서 접근 방지
        accessTokenCookie.setSecure(true); // HTTPS 연결에서만 사용
        accessTokenCookie.setPath("/");
        accessTokenCookie.setMaxAge(60 * 60); // 1시간 유효

        log.info("[login 카카오에 토큰 요청 API] Refresh Token 쿠키에 담기");
        Cookie refreshTokenCookie = new Cookie("refreshToken", responseDto.getRefreshToken());
        refreshTokenCookie.setHttpOnly(true); // JavaScript에서 접근 방지
        refreshTokenCookie.setSecure(true); // HTTPS 연결에서만 사용
        refreshTokenCookie.setPath("/");
        refreshTokenCookie.setMaxAge(24 * 60 * 60); // 1일 유효

        log.info("[login 카카오에 토큰 요청 API] httpServletResponse에 쿠키 담기");
        httpServletResponse.addCookie(accessTokenCookie);
        httpServletResponse.addCookie(refreshTokenCookie);

        log.info("[login 카카오에 토큰 요청 API] 토큰으로 사용자 정보 가져오기");
        KakaoUserInfoResponseDto userInfo = authService.checkUserInfo(responseDto.getAccessToken());

        log.info("[login 카카오에 토큰 요청 API] Redis에 Refresh 토큰을 저장(kakao_id, refresh_token, access_token)");
        refreshTokenRepository.save(new RefreshToken(String.valueOf(userInfo.getId()), responseDto.getRefreshToken(), responseDto.getAccessToken()));

        log.info("[login 카카오에 토큰 요청 API] kakao id로 auth 엔티티 찾기");
        Optional<Auth> optionalAuth = authRepository.findByKakaoUid(String.valueOf(userInfo.getId()));

        RedirectView redirectView = new RedirectView();

        if(optionalAuth.isEmpty()) {
            log.info("[login 카카오에 토큰 요청 API] auth 엔티티 존재하지 않음. 따라서 auth 엔티티, userInfoTmp 엔티티 생성");
            authService.generateAuthAndUserInfoTmp(userInfo);

            log.info("[login 카카오에 토큰 요청 API] 클라이언트를 동의 구하기 페이지로 리다이렉트");
            redirectView.setUrl(kakaoRedirectUriSignup);
        }
        else {
            log.info("[login 카카오에 토큰 요청 API] 클라이언트를 메인 페이지로 리다이렉트");
            redirectView.setUrl(kakaoRedirectUriHome);
        }

        return redirectView;
    }

    @GetMapping("/consent-starcast")
    public ResponseEntity<BaseResponseTemplate<?>> updateUserInfoTmp(
            @RequestBody UpdateUserInfoTmpRequest request,
            @RequestHeader(value = "Authorization") String accessToken) {

        log.info("[login 스타캐스트 요구 사항으로 UserTmpInfo 업데이트하기 API] GET /api/v1/auth/consent-starcast");
        if (!request.isConsentGps()) throw new IllegalStateException("[login 스타캐스트 요구 사항으로 UserTmpInfo 업데이트하기 API] consent_gps는 true여야 합니다.");

        RefreshToken refreshToken = refreshTokenRepository.findByAccessToken(accessToken)
                .orElseThrow(() -> new IllegalStateException("[login 스타캐스트 요구 사항으로 UserTmpInfo 업데이트하기 API] refresh token 값이 없습니다."));

        log.info("[login 스타캐스트 요구 사항으로 UserTmpInfo 업데이트하기 API] accessToken과 refreshToken으로 kakaoId 가져오기");
        String kakaoId = String.valueOf(authService.getKakaoId(accessToken, refreshToken.getRefreshToken()).getId());

        Auth auth = authRepository.findByKakaoUid(kakaoId)
                .orElseThrow(() -> new IllegalStateException("Auth not found for Kakao ID: " + kakaoId));

        log.info("[login 스타캐스트 요구 사항으로 UserTmpInfo 업데이트하기 API] auth 존재함");
        UpdateUserInfoTmpResponse response = authService.updateUserInfoTmp(auth, request.isConsentGps(), request.isConsentNotice());
        BaseResponseTemplate<UpdateUserInfoTmpResponse> successResponse = BaseResponseTemplate.success(response);

        return ResponseEntity.ok().body(successResponse);
    }
}
