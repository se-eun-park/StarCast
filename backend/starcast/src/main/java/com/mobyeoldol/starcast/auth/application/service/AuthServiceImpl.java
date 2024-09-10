package com.mobyeoldol.starcast.auth.application.service;

import com.mobyeoldol.starcast.auth.application.dto.*;
import com.mobyeoldol.starcast.auth.domain.repository.AuthRepository;
import com.mobyeoldol.starcast.auth.domain.repository.ProfileRepository;
import com.mobyeoldol.starcast.auth.domain.Auth;
import com.mobyeoldol.starcast.auth.domain.Profile;
import com.mobyeoldol.starcast.auth.presentation.exception.CustomErrorCode;
import com.mobyeoldol.starcast.auth.presentation.exception.CustomException;
import io.netty.handler.codec.http.HttpHeaderValues;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;


@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final ProfileRepository profileRepository;

    private final AuthRepository authRepository;

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

        KakaoAuthenticateUserResponseDto dto = authenticateUser(response.getAccessToken());
        Optional<Auth> optionalAuth = authRepository.findAuthByKakaoId(dto.id);
        if(optionalAuth.isEmpty()) {
            saveMember(dto.id, response.accessToken);
        }

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

    @Override
    public KakaoAuthenticateUserResponseDto authenticateUser(String accessToken) {
        log.info("authenticateUser 집입");
//        log.info("accessToken : {}", accessToken);

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
        return userInfo;
    }

    @Override
    public KakaoUnlinkResponseDto unlink(String accessToken) {
        log.info("unlink 집입");
        log.info("accessToken : {}", accessToken);

        WebClient webClient = WebClient.create(kakaoApiUri);

        KakaoUnlinkResponseDto unlink = webClient
                .post()
                .uri(uriBuilder -> uriBuilder
                        .scheme("https")
                        .path("/v1/user/unlink")
                        .build(true))
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> Mono.error(new RuntimeException("Invalid Parameter")))
                .onStatus(HttpStatusCode::is5xxServerError, clientResponse -> Mono.error(new RuntimeException("Internal Server Error")))
                .bodyToMono(KakaoUnlinkResponseDto.class)
                .block();
        log.info("unlink - id: {}", unlink.getId());
        return unlink;
    }

    @Transactional
    @Override
    public KakaoUserInfoResponseDto getUserInfo(String accessToken) {
        log.info("getUserInfo 집입");
//        log.info("accessToken : {}", accessToken);

        WebClient webClient = WebClient.create("https://kapi.kakao.com");
        KakaoUserInfoResponseDto userInfo;

        userInfo = webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .scheme("https")
                        .path("/v1/user/access_token_info")
                        .build(true))
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
                .header(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded;charset=utf-8")
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> Mono.error(new RuntimeException("Invalid Parameter")))
                .onStatus(HttpStatusCode::is5xxServerError, clientResponse -> Mono.error(new RuntimeException("Internal Server Error")))
                .bodyToMono(KakaoUserInfoResponseDto.class)
                .block();

        log.info("user info - id: {}", userInfo.getId());

        return userInfo;
    }

    public UUID generateType4UUID() {
        // 버전 4 UUID 생성하기
        UUID uuid4 = UUID.randomUUID();
//        System.out.println("Version 4 UUID: " + uuid4); // Version 4 UUID: c48b2aef-9d79-44fe-bd97-46fd31361069
        return uuid4;
    }

    @Transactional
    public void saveMember(Long kakaoId, String accessToken) {

        KakaoUserInfoResponseDto kakaoUserInfoResponseDto;
        String generatedId = "AU" + generateType4UUID();
        Auth auth = Auth.builder()
                .authId(generatedId)
                .kakaoId(kakaoId)
                .createdDate(LocalDateTime.now())
                .modifiedDate(LocalDateTime.now())
                .isDeleted(false)
                .build();

        Auth result = authRepository.save(auth);
        if(result.getAuthId().isEmpty()) {
            log.info("Auth 저장 실패");
            throw new CustomException(CustomErrorCode.AUTH_NOT_CREATED);
        }

        kakaoUserInfoResponseDto = getUserInfo(accessToken);
        if(kakaoUserInfoResponseDto.getId() == null) throw new CustomException(CustomErrorCode.USER_INFO_NOT_FOUND);

    }

//    public void createProfile(Long kakaoId, KakaoUserInfoResponseDto dto) {
//        String generatedId = "PR" + generateType4UUID();
//        Profile profile = Profile.builder()
//                .profileId(generatedId)
//                .authId(kakaoId)
//                .createdDate(LocalDateTime.now())
//                .modifiedDate(LocalDateTime.now())
//                .isDeleted(false)
//                .build();
//
//        profileRepository.save(profile);
//        if(!authRepository.existsById(generatedId)) throw new CustomException(CustomErrorCode.AUTH_NOT_CREATED);
//    }


//    @Override
//    public String logoutWithKakaoAccount() {
//        return kakaoAuthUri + "/oauth/logout?client_id=" +
//                kakaoClientId + "&logout_redirect_uri=" +
//                kakaoRedirectUriLogout;
//    }
}
