package com.mobyeoldol.starcast.auth.application.service;

import com.mobyeoldol.starcast.auth.application.dto.KakaoTokenInfoResponseDto;
import com.mobyeoldol.starcast.auth.application.dto.KakaoTokenResponseDto;
import com.mobyeoldol.starcast.auth.application.dto.KakaoUserInfoResponseDto;
import com.mobyeoldol.starcast.auth.domain.Auth;
import com.mobyeoldol.starcast.auth.domain.UserInfoTmp;
import com.mobyeoldol.starcast.auth.domain.repository.AuthRepository;
import com.mobyeoldol.starcast.auth.domain.repository.RefreshTokenRepository;
import com.mobyeoldol.starcast.auth.domain.repository.UserInfoTmpRepository;
import com.mobyeoldol.starcast.auth.presentation.response.UpdateUserInfoTmpResponse;
import com.mobyeoldol.starcast.member.domain.repository.ProfileRepository;
import com.mobyeoldol.starcast.member.domain.repository.RankRepository;
import io.netty.handler.codec.http.HttpHeaderValues;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.http.HttpStatusCode;
import reactor.core.publisher.Mono;

import java.util.Optional;
import java.util.UUID;


@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

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

    private final AuthRepository authRepository;
    private final UserInfoTmpRepository userInfoTmpRepository;
    private final ProfileRepository profileRepository;
    private final RankRepository rankRepository;
    private final RefreshTokenRepository refreshTokenRepository;

    @Override
    public String getAccessCode() {

        log.info("[login 유효 인가 코드 요청 API] 1. 카카오에 유효한 정보 전달");
        return kakaoAuthUri + "/oauth/authorize?client_id=" +
                kakaoClientId + "&redirect_uri=" +
                kakaoRedirectUriLogin + "&response_type=code";
    }

    @Override
    public KakaoTokenResponseDto getAccessToken(String code) {

        log.info("[login 카카오에 토큰 요청 API] 1. WebClient로 유효 인가 코드 카카오에 보내서 토큰 요청");
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

        log.info("[login 카카오에 토큰 요청 API] 2. 카카오에서 토큰 수령 성공");
        return dto;
    }

    @Override
    public KakaoUserInfoResponseDto checkUserInfo(String accessToken) {

        log.info("[login 카카오에 토큰 요청 API] 3. WebClient로 access token 카카오에 보내서 사용자 정보 요청");
        WebClient webClient = WebClient.create(kakaoApiUri);
        KakaoUserInfoResponseDto userInfo = webClient
                .get()
                .uri(uriBuilder -> uriBuilder.scheme("https").path("/v2/user/me").build())
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> Mono.error(new RuntimeException("Token expired or invalid parameter")))
                .onStatus(HttpStatusCode::is5xxServerError, clientResponse -> Mono.error(new RuntimeException("Internal Server Error")))
                .bodyToMono(KakaoUserInfoResponseDto.class)
                .block();

        log.info("[login 카카오에 토큰 요청 API] 4. 유저 정보 리턴 성공");
        return userInfo;
    }

    public String refreshAccessToken(String refreshToken) {

        log.info("[refreshToken으로 accessToken 재발급 받기] 1. WebClient로 유효 인가 코드 카카오에 보내서 토큰 요청");
        WebClient webClient = WebClient.create(kakaoAuthUri);
        KakaoTokenResponseDto tokenResponse = webClient
                .post()
                .uri(uriBuilder -> uriBuilder
                        .path("/oauth/token")
                        .queryParam("grant_type", "refresh_token")
                        .queryParam("client_id", kakaoClientId)
                        .queryParam("refresh_token", refreshToken)
                        .build())
                .header(HttpHeaders.CONTENT_TYPE, HttpHeaderValues.APPLICATION_X_WWW_FORM_URLENCODED.toString())
                .retrieve()
                .bodyToMono(KakaoTokenResponseDto.class)
                .block();

        log.info("[refreshToken으로 accessToken 재발급 받기] 2. 카카오에서 토큰 수령 성공");
        return tokenResponse.getAccessToken();
    }

    @Override
    public void generateAuthAndUserInfoTmp(KakaoUserInfoResponseDto dto) {

        log.info("[login 카카오에 토큰 요청 API] 5-1-1. auth 엔티티 생성");
        Auth auth = Auth.builder()
                .authId(UUID.randomUUID().toString())
                .kakaoUid(String.valueOf(dto.getId()))
                .build();

        authRepository.save(auth);
        if(authRepository.findByKakaoUid(auth.getKakaoUid()).isEmpty()) {
            log.info("[login 카카오에 토큰 요청 API] 5-1-2-1. auth 엔티티 저장 실패");
            throw new IllegalStateException("[login 카카오에 토큰 요청 API] auth 엔티티 저장 실패");
        }
        log.info("[login 카카오에 토큰 요청 API] 5-1-2-2. auth 엔티티 저장 성공");

        log.info("[login 카카오에 토큰 요청 API] 5-1-3. userInfoTmp 엔티티 생성");
        UserInfoTmp userInfoTmp = UserInfoTmp.builder()
                .userInfoTmpUid(UUID.randomUUID().toString())
                .auth(auth)
                .email(dto.getKakaoAccount().getEmail())
                .name(dto.getKakaoAccount().getProfile().getNickname())
                .consentGps(false)
                .consentNotice(false)
                .build();

        userInfoTmpRepository.save(userInfoTmp);
        if(userInfoTmpRepository.findByAuth(auth).isEmpty()) {
            log.info("[login 카카오에 토큰 요청 API] 5-1-3-1 userInfoTmp 엔티티 저장 실패");
            throw new IllegalStateException("[login 카카오에 토큰 요청 API] userInfoTmp 엔티티 저장 실패");
        }
        log.info("[login 카카오에 토큰 요청 API] 5-1-3-2. userInfoTmp 엔티티 저장 성공");
    }

    @Override
    public KakaoTokenInfoResponseDto getKakaoId(String accessToken, String refreshToken) {

        log.info("[login 스타캐스트 요구 사항으로 UserTmpInfo 업데이트하기 API] 1. accessToken으로 kakaoId 가져오기");
        try {
            WebClient webClient = WebClient.create(kakaoApiUri);
            KakaoTokenInfoResponseDto tokenInfo = webClient
                    .get()
                    .uri(uriBuilder -> uriBuilder
                            .scheme("https")
                            .path("/v1/user/access_token_info")
                            .build(true))
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
                    .header(HttpHeaders.CONTENT_TYPE, HttpHeaderValues.APPLICATION_X_WWW_FORM_URLENCODED.toString())
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> {
                        // 401 에러 시 새 토큰 발급 시도
                        if (clientResponse.statusCode() == HttpStatus.UNAUTHORIZED) {
                            log.info("[login 스타캐스트 요구 사항으로 UserTmpInfo 업데이트하기 API] 2-1-1-1. access token 만료");
                            return Mono.error(new RuntimeException("TokenExpired"));
                        }
                        log.info("[login 스타캐스트 요구 사항으로 UserTmpInfo 업데이트하기 API] 2-1-2. 유효하지 않은 매개변수 전달");
                        return Mono.error(new RuntimeException("Invalid Parameter"));
                    })
                    .onStatus(HttpStatusCode::is5xxServerError, clientResponse -> Mono.error(new RuntimeException("Internal Server Error")))
                    .bodyToMono(KakaoTokenInfoResponseDto.class)
                    .block();

            log.info("[login 스타캐스트 요구 사항으로 UserTmpInfo 업데이트하기 API] 2-2. 유저 정보 리턴 성공");
            return tokenInfo;
        } catch (Exception e) {
            if (e.getMessage().equals("TokenExpired")) {
                log.info("[login 스타캐스트 요구 사항으로 UserTmpInfo 업데이트하기 API] 2-1-1-2. refreshToken으로 새로운 accessToken 발급");
                String newAccessToken = refreshAccessToken(refreshToken);

                log.info("[login 스타캐스트 요구 사항으로 UserTmpInfo 업데이트하기 API] 2-1-1-3. 새로운 accessToken으로 사용자 정보 요청 다시 시도");
                return getKakaoId(newAccessToken, refreshToken); // 갱신된 토큰으로 다시 시도
            } else {
                log.info("[login 스타캐스트 요구 사항으로 UserTmpInfo 업데이트하기 API] 2-1-3. 다른 종류의 4xx에러 발생. error : {}", e.getMessage());
                throw new IllegalStateException("[login 스타캐스트 요구 사항으로 UserTmpInfo 업데이트하기 API] 다른 종류의 4xx에러 발생");
            }
        }
    }

    @Override
    public UpdateUserInfoTmpResponse updateUserInfoTmp(Auth auth, boolean consentGps, boolean consentNotice) {

        log.info("[login 스타캐스트 요구 사항으로 UserTmpInfo 업데이트하기 API] 1. userInfoTmp 엔티티 검색");
        UserInfoTmp userInfoTmp = userInfoTmpRepository.findByAuth(auth)
                .orElseThrow(() -> new IllegalStateException("[login 스타캐스트 요구 사항으로 UserTmpInfo 업데이트하기 API] userInfoTmp 엔티티를 찾을 수 없습니다."));

        log.info("[login 스타캐스트 요구 사항으로 UserTmpInfo 업데이트하기 API] 2. userInfoTmp의 consentGps 부분 수정");
        userInfoTmp.setConsentGps(consentGps);
        log.info("[login 스타캐스트 요구 사항으로 UserTmpInfo 업데이트하기 API] 3. userInfoTmp의 consentNotice 부분 수정");
        userInfoTmp.setConsentNotice(consentNotice);

        log.info("[login 스타캐스트 요구 사항으로 UserTmpInfo 업데이트하기 API] 4. 수정된 userInfoTmp 저장");
        userInfoTmpRepository.save(userInfoTmp);

        return UpdateUserInfoTmpResponse.builder()
                .userInfoTmpUid(userInfoTmpRepository.findByAuth(auth).get().getUserInfoTmpUid())
                .build();
    }

//    @Override
//    public UpdateUserInfoTmpResponse generateProfile(Auth auth, boolean consentGps, boolean consentNotice) {
//
//        Rank rankTmp = rankRepository.findByName("Basic");
//
//        log.info("[login 카카오에 토큰 요청 API] 5-2-1. profile 엔티티 생성");
//        Profile profile = Profile.builder()
//                .profileUid(UUID.randomUUID().toString())
//                .auth(userInfoTmp.getAuth())
//                .rank(rankTmp)
//                .email(userInfoTmp.getEmail())
//                .name(userInfoTmp.getName())
//                .profileImgNum("CASTAR1")
//                .exp(0)
//                .isDeleted(false)
//                .actionPlaceType("GPS")
//                .build();
//
//        profileRepository.save(profile);
//        if(profileRepository.findById(profile.getProfileUid()).isEmpty()) {
//            log.info("[login 카카오에 토큰 요청 API] 5-2-2-1 profile 엔티티 저장 실패");
//            throw new IllegalStateException("[login 카카오에 토큰 요청 API] profile 엔티티 저장 실패");
//        }
//        log.info("[login 카카오에 토큰 요청 API] 5-2-2-2. profile 엔티티 저장 성공");
//    }

    @Override
    public String authenticateMember(String accessToken) {

        log.info("[로그인한 사용자 인증하기] TMP");

//        WebClient webClient = WebClient.create(kakaoApiUri);
//        KakaoUserInfoResponseDto userInfo = webClient
//                .get()
//                .uri(uriBuilder -> uriBuilder
//                        .scheme("https")
//                        .path("/v2/user/me")
//                        .build(true))
//                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
//                .header(HttpHeaders.CONTENT_TYPE, HttpHeaderValues.APPLICATION_X_WWW_FORM_URLENCODED.toString())
//                .retrieve()
//                .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> Mono.error(new RuntimeException("Invalid Parameter")))
//                .onStatus(HttpStatusCode::is5xxServerError, clientResponse -> Mono.error(new RuntimeException("Internal Server Error")))
//                .bodyToMono(KakaoUserInfoResponseDto.class)
//                .block();
//
//        log.info("[login 카카오에 토큰 요청 API] 4. 카카오에서 사용자 정보 수령 성공");
//        return userInfo;
        return "profile-uid-01";
    }
}
