package com.mobyeoldol.starcast.auth.application.service;

import com.mobyeoldol.starcast.auth.application.dto.KakaoTokenResponseDto;
import com.mobyeoldol.starcast.auth.application.dto.KakaoUserInfoResponseDto;
import com.mobyeoldol.starcast.auth.domain.Auth;
import com.mobyeoldol.starcast.auth.domain.UserInfoTmp;
import com.mobyeoldol.starcast.auth.domain.repository.AuthRepository;
import com.mobyeoldol.starcast.auth.domain.repository.UserInfoTmpRepository;
import io.netty.handler.codec.http.HttpHeaderValues;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.http.HttpStatusCode;
import reactor.core.publisher.Mono;

import java.util.UUID;


@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

    private final UserInfoTmpRepository userInfoTmpRepository;
    @Value("${kakao.auth.uri}")
    String kakaoAuthUri;

    @Value("${kakao.api.uri}")
    String kakaoApiUri;

    @Value("${kakao.client.id}")
    String kakaoClientId;

    @Value("${kakao.redirect.uri.login}")
    String kakaoRedirectUriLogin;

    @Value("${kakao.redirect.uri.logout}")
    String kakaoRedirectUriLogout;

    @Value("${kakao.response.type}")
    String kakaoResponseType;

    @Value("${kakao.grant.type}")
    String kakaoGrantType;

    private AuthRepository authRepository;
    private UserInfoTmp userInfoTmp;

    @Override
    public String getAccessCode() {
        return kakaoAuthUri + "/oauth/authorize?client_id=" +
                kakaoClientId + "&redirect_uri=" +
                kakaoRedirectUriLogin + "&response_type=code";
    }

    @Override
    public KakaoTokenResponseDto getAccessToken(String code) {

        WebClient webClient = WebClient.create(kakaoAuthUri);
        KakaoTokenResponseDto dto = webClient
                .post()
                .uri(uriBuilder -> uriBuilder
                        .scheme("https")
                        .path("/oauth/token")
                        .queryParam("grant_type", kakaoGrantType)
                        .queryParam("client_id", kakaoClientId)
                        .queryParam("redirect_uri", kakaoRedirectUriLogin)
                        .queryParam("code", code)
                        .build(true))
                .header(HttpHeaders.CONTENT_TYPE, HttpHeaderValues.APPLICATION_X_WWW_FORM_URLENCODED.toString())
                .retrieve()
                .bodyToMono(KakaoTokenResponseDto.class)
                .block();

        return dto;
    }

    @Override
    public KakaoUserInfoResponseDto checkUserInfo(String accessToken) {
        log.info("checkUserInfoTmp 집입");

        WebClient webClient = WebClient.create(kakaoApiUri);

        KakaoUserInfoResponseDto userInfo = webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .scheme("https")
                        .path("/v2/user/me")
                        .build(true))
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
                .header(HttpHeaders.CONTENT_TYPE, HttpHeaderValues.APPLICATION_X_WWW_FORM_URLENCODED.toString())
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> Mono.error(new RuntimeException("Invalid Parameter")))
                .onStatus(HttpStatusCode::is5xxServerError, clientResponse -> Mono.error(new RuntimeException("Internal Server Error")))
                .bodyToMono(KakaoUserInfoResponseDto.class)
                .block();
        log.info("user info - id: {}", userInfo.getId());

        return userInfo;
    }

    @Override
    public void generateAuthAndUserInfoTmp(KakaoUserInfoResponseDto dto) {

        Auth auth = Auth.builder()
                .authId(UUID.randomUUID().toString())
                .kakaoUid(dto.getId())
                .build();

        authRepository.save(auth);
        if(authRepository.findByKakaoUid(auth.getKakaoUid()).isEmpty()) throw new IllegalStateException("[Auth 등록 API] Auth 저장에 실패하였습니다.");

        UserInfoTmp userInfoTmp = UserInfoTmp.builder()
                .userInfoTmpUid(UUID.randomUUID().toString())
                .auth(auth)
                .email(dto.getKakaoAccount().getEmail())
                .name(dto.getKakaoAccount().getProfile().getNickname())
                .consentGps(false)
                .consentNotice(false)
                .build();

        userInfoTmpRepository.save(userInfoTmp);
    }

    @Override
    public String authenticateMember(String accessToken) {

        log.info("[로그인한 사용자 인증하기] TMP");
        return "profile-uid-01";
    }
}
