package com.mobyeoldol.starcast.member.presentation;

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

    @PatchMapping("/update-address")
    public ResponseEntity<?> updateMySpot(
            @RequestHeader("Authorization") String bearerToken,
            @Valid @RequestBody UpdateMySpotRequest request,
            BindingResult bindingResult)
    {
        log.info("[나의 정보 수정 (내 주소) API] PATCH /api/v1/member/update-address");

        if (bindingResult.hasErrors()) {
            log.info("[나의 정보 수정 (내 주소) API] 입력값인 주소를 찾을 수 없는 경우 400 반환");
            throw new IllegalArgumentException("주소1, 주소2, 주소4는 필수 항목입니다.");
        }

        String profileUid = ""; // authenticateMember(bearerToken);

        log.info("[나의 정보 수정 (내 주소) API] 내 주소 수정 Service 로직 수행");
        memberService.updateMySpot(profileUid, request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PatchMapping("/update-nickname/{nickname}")
    public ResponseEntity<?> updateMyNickname(
            @RequestHeader("Authorization") String bearerToken,
            @PathVariable String nickname)
    {
        log.info("[나의 정보 수정 (닉네임) API] PATCH /api/v1/place/update-nickname");

        if (nickname == null || nickname.trim().isEmpty()) {
            log.info("[나의 정보 수정 (닉네임) API] 입력값인 닉네임을 찾을 수 없는 경우 400 반환");
            throw new IllegalArgumentException("닉네임을 입력해주세요.");
        }

        String profileUid = ""; // authenticateMember(bearerToken);

        log.info("[나의 정보 수정 (닉네임) API] 닉네임 수정 Service 로직 수행");
        memberService.updateMyNickname(profileUid, nickname);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PatchMapping("/update-profile-image/{image}")
    public ResponseEntity<?> updateMyProfileImage(
            @RequestHeader("Authorization") String bearerToken,
            @PathVariable String image)
    {
        log.info("[나의 정보 수정 (캐스타이미지) API] PATCH /api/v1/member/update-profile-image");

        if (image == null || image.trim().isEmpty()) {
            log.info("[나의 정보 수정 (캐스타이미지) API] 입력값인 이미지를 찾을 수 없는 경우 400 반환");
            throw new IllegalArgumentException("이미지를 입력해주세요.");
        }

        String profileUid = ""; // authenticateMember(bearerToken);

        log.info("[나의 정보 수정 (캐스타이미지) API] 캐스타이미지 수정 Service 로직 수행");
        memberService.updateMyProfileImage(profileUid, image);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


    @GetMapping("/info")
    public ResponseEntity<MyInfoResponse> getMyInfo(@RequestHeader("Authorization") String bearerToken) {
        log.info("[내 정보 가져오기 API] GET /api/v1/member/info");
//        String profileUid = authenticateMember(bearerToken);
        String profileUid = "profile-uid-01";

        log.info("[내 정보 가져오기 API] 내 정보 가져오기 Service 로직 수행");
        MyInfoResponse myInfo = memberService.getMemberInfo(profileUid);

        return ResponseEntity.status(HttpStatus.OK).body(myInfo);
    }

    @GetMapping("/my-community-list")
    public ResponseEntity<?> getCommunityListByMember(@RequestHeader("Authorization") String bearerToken) {
        log.info("[내가 작성한 글 리스트 가져오기 API] GET /api/v1/member/my-community-list");
//        String profileUid = authenticateMember(bearerToken);
        String profileUid = "profile-uid-01";

        log.info("[내가 작성한 글 리스트 가져오기 API] 글 리스트 가져오기 Service 로직 수행");
        List<CommunityByMemberResponse> communities = memberService.getCommunityListByMember(profileUid);

        return ResponseEntity.status(HttpStatus.OK).body(communities);
    }

    @GetMapping("/my-reaction")
    public ResponseEntity<MyReactionResponse> getMyReactions(@RequestHeader("Authorization") String bearerToken) {
        log.info("[작성한 나의 반응 API] GET /api/v1/member/my-reaction");
        String profileUid = ""; // authenticateMember(bearerToken);

        log.info("[작성한 나의 반응 API] Service 로직 수행");
        return ResponseEntity.ok().body(memberService.getMyReactions(profileUid));
    }
}
