package com.mobyeoldol.starcast.auth.application.service;

import com.mobyeoldol.starcast.auth.application.dto.*;
import io.netty.handler.codec.http.HttpHeaderValues;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


@Slf4j
@Service
public class AuthServiceImpl implements AuthService {

    @Value("$ {kakao.auth.uri}")
    String kakaoAuthUri;

    @Value("$ {kakao.api.uri}")
    String kakaoApiUri;

    @Value("$ {kakao.client.id}")
    String kakaoClientId;

    @Value("$ {kakao.redirect.login}")
    String kakaoRedirectUriLogin;

    @Value("$ {kakao.redirect.logout}")
    String kakaoRedirectUriLogout;

    @Value("$ {kakao.response.type}")
    String kakaoResponseType;

    @Value("$ {kakao.grant.type}")
    String kakaoGrantType;

    @Override
    public String getAccessCode() {
        return kakaoAuthUri + "/oauth/authorize?client_id=" +
                kakaoClientId + "&redirect_uri=" +
                kakaoRedirectUriLogin + "&response_type=code";
    }

    @Override
    public String getAccessToken(String code) {

        WebClient webClient = WebClient.create(kakaoAuthUri);

        KakaoTokenResponseDto response = webClient
                .post()
                .uri(uriBuilder -> uriBuilder
                        .scheme("https")
                        .path("/oauth/token")
                        .queryParam("grant_type", kakaoGrantType)
                        .queryParam("client_id", kakaoClientId)
                        .queryParam("code", code)
                        .build(true))
                .header(HttpHeaders.CONTENT_TYPE, HttpHeaderValues.APPLICATION_X_WWW_FORM_URLENCODED.toString())
                .retrieve()
                .bodyToMono(KakaoTokenResponseDto.class)
                .block();

        return response.getAccessToken();
    }

    @Override
    public KakaoLogoutResponseDto logout(String accessToken) {
        log.info("logout 집입");
        log.info("accessToken : {}", accessToken);

        WebClient webClient = WebClient.create(kakaoApiUri);

        KakaoLogoutResponseDto logout = webClient
                .post()
                .uri(uriBuilder -> uriBuilder
                        .scheme("https")
                        .path("/v1/user/logout")
                        .build(true))
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> Mono.error(new RuntimeException("Invalid Parameter")))
                .onStatus(HttpStatusCode::is5xxServerError, clientResponse -> Mono.error(new RuntimeException("Internal Server Error")))
                .bodyToMono(KakaoLogoutResponseDto.class)
                .block();
        log.info("logout - id: {}", logout.getId());
        return logout;
    }
}
