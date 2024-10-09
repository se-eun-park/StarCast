package com.mobyeoldol.starcast.auth.application.service;

import com.mobyeoldol.starcast.auth.application.dto.KakaoTokenInfoResponseDto;
import com.mobyeoldol.starcast.auth.domain.Auth;
import com.mobyeoldol.starcast.auth.domain.UserInfoTmp;
import com.mobyeoldol.starcast.auth.domain.repository.AuthRepository;
import com.mobyeoldol.starcast.auth.domain.repository.UserInfoTmpRepository;
import com.mobyeoldol.starcast.auth.presentation.response.LogoutResponse;
import com.mobyeoldol.starcast.auth.presentation.response.UnlinkResponse;
import com.mobyeoldol.starcast.member.domain.Profile;
import com.mobyeoldol.starcast.member.domain.Rank;
import com.mobyeoldol.starcast.member.domain.repository.ProfileRepository;
import com.mobyeoldol.starcast.member.domain.repository.RankRepository;
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

    @Override
    public String authenticateMember(String bearerToken) {

        log.info("[authenticateMember : accessToken으로 profileUid 리턴하기] 1. getTokenInfo 메서드 호출");
        KakaoTokenInfoResponseDto dto = getTokenInfo(bearerToken);

        log.info("[authenticateMember : accessToken으로 profileUid 리턴하기] 1. tokenInfoToAuth 메서드 호출");
        Auth auth = tokenInfoToAuth(dto);

        log.info("[authenticateMember : accessToken으로 profileUid 리턴하기] 2. authToProfile 메서드 호출");
        return authToProfileUid(auth);
    }

    @Override
    public KakaoTokenInfoResponseDto getTokenInfo(String bearerToken) {

        log.info("[accessTokenToAuth : accessToken으로 Auth 리턴하기] 1. 카카오로 accessToken 전송");
        WebClient webClient = WebClient.create(kakaoApiUri);
        KakaoTokenInfoResponseDto tokenInfo = webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .scheme("https")
                        .path("/v1/user/access_token_info")
                        .build(true))
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + bearerToken)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> Mono.error(new RuntimeException("Invalid Parameter")))
                .onStatus(HttpStatusCode::is5xxServerError, clientResponse -> Mono.error(new RuntimeException("Internal Server Error")))
                .bodyToMono(KakaoTokenInfoResponseDto.class)
                .block();

        return tokenInfo;
    }

    @Override
    public Auth tokenInfoToAuth(KakaoTokenInfoResponseDto dto) {

        log.info("[accessTokenToAuth : accessToken으로 Auth 리턴하기] 2. 카카오 id로 auth 찾아 리턴");
        return authRepository.findByKakaoUid(String.valueOf(dto.getId()))
                .orElseThrow(() -> new IllegalArgumentException("[accessTokenToAuth : accessToken으로 Auth 리턴하기] auth 정보가 존재하지 않습니다."));
    }

    @Override
    public String authToProfileUid(Auth auth) {

        log.info("[authToProfile : auth로 profileUid 리턴하기] 3. auth로 profileUid 찾기");
        Profile profile = profileRepository.findByAuth(auth)
                .orElseThrow(() -> new IllegalArgumentException("[authToProfile : auth로 profileUid 리턴하기] profile 정보가 존재하지 않습니다."));

        return profile.getProfileUid();
    }

    @Override
    public LogoutResponse logout(String bearerToken) {

        log.info("[로그아웃 API] 1. 카카오로 토큰 전송해서 로그아웃 요청");
        WebClient webClient = WebClient.create(kakaoApiUri);
        LogoutResponse response = webClient
                .post()
                .uri(uriBuilder -> uriBuilder
                        .scheme("https")
                        .path("/v1/user/logout")
                        .build(true))
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + bearerToken)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> Mono.error(new RuntimeException("Invalid Parameter")))
                .onStatus(HttpStatusCode::is5xxServerError, clientResponse -> Mono.error(new RuntimeException("Internal Server Error")))
                .bodyToMono(LogoutResponse.class)
                .block();

        log.info("[로그아웃 API] 2. 카카오 id 리턴");
        return response;
    }

    @Override
    public UnlinkResponse unlink(String bearerToken) {

        log.info("[탈퇴 API] 1. 카카오로 토큰 전송해서 탈퇴 요청");
        WebClient webClient = WebClient.create(kakaoApiUri);
        UnlinkResponse response = webClient
                .post()
                .uri(uriBuilder -> uriBuilder
                        .scheme("https")
                        .path("/v1/user/unlink")
                        .build(true))
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + bearerToken)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> Mono.error(new RuntimeException("Invalid Parameter")))
                .onStatus(HttpStatusCode::is5xxServerError, clientResponse -> Mono.error(new RuntimeException("Internal Server Error")))
                .bodyToMono(UnlinkResponse.class)
                .block();

        log.info("[탈퇴 API] 2. 받은 카카오 id로 auth 찾기");
        Auth auth = authRepository.findByKakaoUid(String.valueOf(response.getId()))
                .orElseThrow(() -> new IllegalArgumentException("[탈퇴 API] auth 정보가 존재하지 않습니다."));

        log.info("[탈퇴 API] 3. auth로 profile 찾기");
        Profile profile = profileRepository.findByAuth(auth)
                .orElseThrow(() -> new IllegalArgumentException("[탈퇴 API] profile 정보가 존재하지 않습니다."));

        log.info("[탈퇴 API] 4. profileUid로 회원 정보 삭제");
        profile.setIsDeleted(true);
        profileRepository.save(profile);

        return response;
    }

    @Override
    public void generateProfile(String nickname, String bearerToken) {

        KakaoTokenInfoResponseDto dto = getTokenInfo(bearerToken);
        Auth auth = tokenInfoToAuth(dto);
        UserInfoTmp userInfoTmp = userInfoTmpRepository.findByAuth(auth)
                .orElseThrow(() -> new IllegalArgumentException("[Profile 만들기 API] profile 정보가 존재하지 않습니다."));
        Rank rankTmp = rankRepository.findByName("Basic");
        String profileUid = UUID.randomUUID().toString();

        log.info("[Profile 만들기 API] 1. userInfoTmp 정보랑 nickname 조합해서 profile 생성");
        Profile profile = Profile.builder()
                .profileUid(profileUid)
                .auth(auth)
                .rank(rankTmp)
                .email(userInfoTmp.getEmail())
                .name(userInfoTmp.getName())
                .nickname(nickname)
                .profileImgNum("CASTAR1")
                .exp(0)
                .isDeleted(false)
                .actionPlaceType("GPS")
                .build();

        profileRepository.save(profile);
        if(profileRepository.findById(profile.getProfileUid()).isEmpty()) {
            log.info("[Profile 만들기 API] 2-1. profile 엔티티 저장 실패");
            throw new IllegalStateException("[Profile 만들기 API] profile 엔티티 저장 실패");
        }

        log.info("[Profile 만들기 API] 2-2. profile 엔티티 저장 성공");
    }
}
