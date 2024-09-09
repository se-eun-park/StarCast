package com.mobyeoldol.starcast.auth.presentation;

import com.mobyeoldol.starcast.auth.application.service.AuthService;
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

        return new ResponseEntity<>(accessToken, HttpStatus.OK);
    }
}
