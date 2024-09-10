package com.mobyeoldol.starcast.auth.presentation;

import com.mobyeoldol.starcast.auth.application.dto.KakaoAuthenticateUserResponseDto;
import com.mobyeoldol.starcast.auth.application.dto.KakaoLogoutResponseDto;
import com.mobyeoldol.starcast.auth.application.dto.KakaoUnlinkResponseDto;
import com.mobyeoldol.starcast.auth.application.dto.KakaoUserInfoResponseDto;
import com.mobyeoldol.starcast.auth.application.service.AuthService;
import com.mobyeoldol.starcast.auth.domain.repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthRepository authRepository;
    private final AuthService authService;
    private String accessToken;

    @GetMapping("/login")
    public RedirectView getAccessCode() {
        RedirectView response = new RedirectView();
        response.setUrl(authService.getAccessCode());
        log.info(response.getUrl());
        return response;
    }

    @GetMapping("/redirect-login")
    public ResponseEntity<?> getAccessToken(@RequestParam("code") String code) {
        log.info("Get access token: {}", code);
        accessToken = authService.getAccessToken(code);
        log.info("카카오 토큰 : {}", accessToken);

//        authService.distributor(accessToken);
        return new ResponseEntity<>(accessToken, HttpStatus.OK);
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logout() {
        log.info("로그아웃 진입");
        KakaoLogoutResponseDto logout = authService.logout(accessToken);
        log.info("로그아웃 성공 logout : {}", logout);

        return new ResponseEntity<>(logout, HttpStatus.OK);
    }

    @GetMapping("/authenticateUser")
    public ResponseEntity<?> authenticateUser() {
        log.info("로그인한 사용자 인증하기");
        KakaoAuthenticateUserResponseDto userInfo = authService.authenticateUser(accessToken);
        log.info("사용자 인증 성공 userInfo : {}", userInfo);

        return new ResponseEntity<>(userInfo, HttpStatus.OK);
    }

    @GetMapping("/unlink")
    public ResponseEntity<?> unlink() {
        log.info("탈퇴 진입");
        KakaoUnlinkResponseDto unlink = authService.unlink(accessToken);
        log.info("탈퇴 성공 unlink : {}", unlink);

        return new ResponseEntity<>(unlink, HttpStatus.OK);
    }

    @GetMapping("/userInfo")
    public ResponseEntity<?> getUserInfo() {
        log.info("getUserInfo 진입");
        KakaoUserInfoResponseDto userInfo = authService.getUserInfo(accessToken);
        log.info("getUserInfo 성공 userInfo : {}", userInfo);

        return new ResponseEntity<>(userInfo, HttpStatus.OK);
    }

//    @GetMapping("/redirect-logout")
//    public RedirectView logoutWithKakaoAccount() {
//        RedirectView response = new RedirectView();
//        response.setUrl(authService.logoutWithKakaoAccount());
//        return response;
//    }

}
