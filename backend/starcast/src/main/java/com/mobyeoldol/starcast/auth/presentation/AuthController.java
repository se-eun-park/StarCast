package com.mobyeoldol.starcast.auth.presentation;

import com.mobyeoldol.starcast.auth.application.service.AuthService;
import com.mobyeoldol.starcast.auth.presentation.response.LogoutResponse;
import com.mobyeoldol.starcast.auth.presentation.response.UnlinkResponse;
import com.mobyeoldol.starcast.global.template.BaseResponseTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    @GetMapping("/logout")
    public ResponseEntity<BaseResponseTemplate<?>> logout(@RequestHeader(value = "Authorization") String bearerToken) {

        log.info("[로그아웃 API] GET /api/v1/auth/logout");
        LogoutResponse response = authService.logout(bearerToken);

        log.info("[로그아웃 API] 로그아웃 후 받은 카카오 id 리턴");
        BaseResponseTemplate<LogoutResponse> successResponse = BaseResponseTemplate.success(response);
        return ResponseEntity.ok().body(successResponse);
    }

    @GetMapping("/unlink")
    public ResponseEntity<BaseResponseTemplate<?>> unlink(@RequestHeader(value = "Authorization") String bearerToken) {

        log.info("[탈퇴 API] GET /api/v1/auth/unlink");
        UnlinkResponse response = authService.unlink(bearerToken);

        log.info("[로그아웃 API] 로그아웃 후 받은 카카오 id 리턴");
        BaseResponseTemplate<UnlinkResponse> successResponse = BaseResponseTemplate.success(response);
        return ResponseEntity.ok().body(successResponse);
    }

    @GetMapping("/nickname/{nickname}")
    public ResponseEntity<BaseResponseTemplate<?>> generateProfile(
            @PathVariable("nickname") String nickname,
            @RequestHeader(value = "Authorization") String bearerToken) {

        log.info("[Profile 만들기 API] GET /api/v1/auth/nickname/{nickname}");
        if(nickname.equals(null)) throw new IllegalStateException("[Profile 만들기 API] nickname은 null일 될 수 없습니다.");

        log.info("[Profile 만들기 API] 새로운 Profile 만들기");
        authService.generateProfile(nickname, bearerToken);

        BaseResponseTemplate<?> successResponse = BaseResponseTemplate.success("프로필이 성공적으로 생성되었습니다.");
        return ResponseEntity.ok().body(successResponse);
    }
}
