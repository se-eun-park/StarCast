package com.mobyeoldol.starcast.member.presentation;

import com.mobyeoldol.starcast.auth.application.service.AuthService;
import com.mobyeoldol.starcast.calendar.presentation.response.MonthlyAstronomicalResponse;
import com.mobyeoldol.starcast.global.template.BaseResponseTemplate;
import com.mobyeoldol.starcast.member.application.service.MemberService;
import com.mobyeoldol.starcast.member.presentation.request.UpdateMySpotRequest;
import com.mobyeoldol.starcast.member.presentation.response.CommunityByMemberResponse;
import com.mobyeoldol.starcast.member.presentation.response.MyInfoResponse;
import com.mobyeoldol.starcast.member.presentation.response.MyReactionResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/member")
public class MemberController {

    private final MemberService memberService;
    private final AuthService authService;

    @PatchMapping("/update-address")
    public ResponseEntity<BaseResponseTemplate<?>> updateMySpot(
            @RequestHeader("Authorization") String bearerToken,
            @Valid @RequestBody UpdateMySpotRequest request,
            BindingResult bindingResult)
    {
        log.info("[나의 정보 수정 (내 주소) API] PATCH /api/v1/member/update-address");

        if (bindingResult.hasErrors()) {
            BaseResponseTemplate<?> errorResponse = BaseResponseTemplate.failure(400, "주소1, 주소2, 주소4는 필수 항목입니다.");
            return ResponseEntity.status(400).body(errorResponse);
        }

        String profileUid = authService.authenticateMember(bearerToken);

        log.info("[나의 정보 수정 (내 주소) API] 내 주소 수정 Service 로직 수행");
        memberService.updateMySpot(profileUid, request);

        log.info("[나의 정보 수정 (내 주소) API] 응답 반환");
        BaseResponseTemplate<?> result = BaseResponseTemplate.success(null);
        return ResponseEntity.ok(result);
    }

    @PatchMapping("/update-nickname/{nickname}")
    public ResponseEntity<BaseResponseTemplate<?>> updateMyNickname(
            @RequestHeader("Authorization") String bearerToken,
            @PathVariable String nickname)
    {
        log.info("[나의 정보 수정 (닉네임) API] PATCH /api/v1/place/update-nickname");

        if (nickname == null || nickname.trim().isEmpty()) {
            BaseResponseTemplate<?> errorResponse = BaseResponseTemplate.failure(400, "닉네임을 입력해주세요.");
            return ResponseEntity.status(400).body(errorResponse);
        }

        String profileUid = authService.authenticateMember(bearerToken);

        log.info("[나의 정보 수정 (닉네임) API] 닉네임 수정 Service 로직 수행");
        memberService.updateMyNickname(profileUid, nickname);

        log.info("[나의 정보 수정 (닉네임) API] 응답 반환");
        BaseResponseTemplate<?> result = BaseResponseTemplate.success(null);
        return ResponseEntity.ok(result);
    }

    @PatchMapping("/update-profile-image/{image}")
    public ResponseEntity<BaseResponseTemplate<?>> updateMyProfileImage(
            @RequestHeader("Authorization") String bearerToken,
            @PathVariable String image)
    {
        log.info("[나의 정보 수정 (캐스타이미지) API] PATCH /api/v1/member/update-profile-image");

        if (image == null || image.trim().isEmpty()) {
            BaseResponseTemplate<?> errorResponse = BaseResponseTemplate.failure(400, "이미지를 입력해주세요.");
            return ResponseEntity.status(400).body(errorResponse);
        }

        String profileUid = authService.authenticateMember(bearerToken);

        log.info("[나의 정보 수정 (캐스타이미지) API] 캐스타이미지 수정 Service 로직 수행");
        memberService.updateMyProfileImage(profileUid, image);

        log.info("[나의 정보 수정 (캐스타이미지) API] 응답 반환");
        BaseResponseTemplate<?> result = BaseResponseTemplate.success(null);
        return ResponseEntity.ok(result);
    }


    @GetMapping("/info")
    public ResponseEntity<BaseResponseTemplate<?>> getMyInfo(@RequestHeader("Authorization") String bearerToken) {
        log.info("[내 정보 가져오기 API] GET /api/v1/member/info");
        String profileUid = authService.authenticateMember(bearerToken);

        log.info("[내 정보 가져오기 API] 내 정보 가져오기 Service 로직 수행");
        MyInfoResponse myInfo = memberService.getMemberInfo(profileUid);

        log.info("[내 정보 가져오기 API] 응답 반환");
        BaseResponseTemplate<?> result = BaseResponseTemplate.success(myInfo);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/my-community-list")
    public ResponseEntity<?> getCommunityListByMember(@RequestHeader("Authorization") String bearerToken) {

        log.info("[내가 작성한 글 리스트 가져오기 API] GET /api/v1/member/my-community-list");
        String profileUid = authService.authenticateMember(bearerToken);

        log.info("[내가 작성한 글 리스트 가져오기 API] 글 리스트 가져오기 Service 로직 수행");
        List<CommunityByMemberResponse> communities = memberService.getCommunityListByMember(profileUid);

        log.info("[내가 작성한 글 리스트 가져오기 API] 응답 반환");
        BaseResponseTemplate<?> result = BaseResponseTemplate.success(communities);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/my-reaction")
    public ResponseEntity<BaseResponseTemplate<?>> getMyReactions(@RequestHeader("Authorization") String bearerToken) {
        log.info("[작성한 나의 반응 API] GET /api/v1/member/my-reaction");
        String profileUid = authService.authenticateMember(bearerToken);

        log.info("[작성한 나의 반응 API] Service 로직 수행");
        MyReactionResponse myReactionResponse = memberService.getMyReactions(profileUid);

        log.info("[작성한 나의 반응 API] 응답 반환");
        BaseResponseTemplate<?> result = BaseResponseTemplate.success(myReactionResponse);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
