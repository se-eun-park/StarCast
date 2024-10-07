package com.mobyeoldol.starcast.auth.presentation;

import com.mobyeoldol.starcast.auth.application.dto.KakaoTokenResponseDto;
import com.mobyeoldol.starcast.auth.application.dto.KakaoUserInfoResponseDto;
import com.mobyeoldol.starcast.auth.application.service.AuthService;
import com.mobyeoldol.starcast.auth.domain.repository.AuthRepository;
import com.mobyeoldol.starcast.auth.presentation.response.LoginResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Cookie;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/oauth")
public class AuthController {

    private final AuthService authService;
    private final AuthRepository authRepository;

    @GetMapping("/login")
    public RedirectView getAccessCode() {
        RedirectView response = new RedirectView();
        response.setUrl(authService.getAccessCode());
        log.info(response.getUrl());
        return response;
    }

    @GetMapping("/redirect-login")
    public ResponseEntity<?> getAccessToken(@RequestParam("code") String code, HttpServletResponse httpServletResponse) {
        log.info("Get access token: {}", code);
        KakaoTokenResponseDto responseDto = authService.getAccessToken(code);

        // Refresh Token을 쿠키에 담기
        Cookie refreshTokenCookie = new Cookie("refreshToken", responseDto.getRefreshToken());
        refreshTokenCookie.setHttpOnly(true); // JavaScript 접근 방지
        refreshTokenCookie.setSecure(true); // HTTPS 연결에서만 사용
        refreshTokenCookie.setPath("/");
        refreshTokenCookie.setMaxAge(24 * 60 * 60); // 1일

        httpServletResponse.addCookie(refreshTokenCookie);

        LoginResponse response = LoginResponse.builder()
                .accessToken(responseDto.getAccessToken())
                .accessTokenExpiresIn(responseDto.getExpiresIn())
                .refreshTokenExpiresIn(responseDto.getRefreshTokenExpiresIn())
                .build();

        KakaoUserInfoResponseDto userInfo = authService.checkUserInfo(responseDto.getAccessToken());

        if(authRepository.findByKakaoUid(userInfo.getId()).isPresent()) return new ResponseEntity<>(response, HttpStatus.OK);
        else {
            authService.generateAuthAndUserInfoTmp(userInfo);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }
}
