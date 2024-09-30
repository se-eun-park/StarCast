package com.mobyeoldol.starcast.auth.application.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class AuthServiceImpl implements AuthService{

    /*
    //original authenticateUser()
    @Override
    public String authenticateUser(String accessToken) {
        log.info("authenticateUser 집입");

        WebClient webClient = WebClient.create("https://kapi.kakao.com");

        KakaoAuthenticateUserResponseDto userInfo = webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .scheme("https")
                        .path("/v1/user/access_token_info")
                        .build(true))
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> Mono.error(new RuntimeException("Invalid Parameter")))
                .onStatus(HttpStatusCode::is5xxServerError, clientResponse -> Mono.error(new RuntimeException("Internal Server Error")))
                .bodyToMono(KakaoAuthenticateUserResponseDto.class)
                .block();
        log.info("user info - id: {}", userInfo.getId());
        return userInfo.getId();
    }

     */

    /*
    @Override
    public String kakaoUidToProfileUid(String kakaoUid) {
        return "";
    }

     */

    @Override
    public String authenticateMember(String accessToken) {

        log.info("[로그인한 사용자 인증하기] TMP");
        return "profile-uid-01";
    }
}
