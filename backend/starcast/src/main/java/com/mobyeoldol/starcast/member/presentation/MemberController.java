package com.mobyeoldol.starcast.member.presentation;

import com.mobyeoldol.starcast.member.application.service.MemberService;
import com.mobyeoldol.starcast.member.presentation.response.CommunityByMemberResponse;
import com.mobyeoldol.starcast.member.presentation.response.MyInfoResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/member")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/info")
    public ResponseEntity<MyInfoResponse> getMyInfo(@RequestHeader("Authorization") String bearerToken) {
        log.info("[내 정보 가져오기 API] GET/api/v1/member/info");
        log.info("[내 정보 가져오기 API] 내 정보 가져오기 Service 로직 수행");
        MyInfoResponse myInfo = memberService.getMemberInfo(bearerToken);

        return ResponseEntity.status(HttpStatus.OK).body(myInfo);
    }

    @GetMapping("/my-community-list")
    public ResponseEntity<?> getCommunityListByMember(@RequestHeader("Authorization") String bearerToken) {
        log.info("[내가 작성한 글 리스트 가져오기 API] GET/api/v1/member/my-community-list");
        log.info("[내가 작성한 글 리스트 가져오기 API] 글 리스트 가져오기 Service 로직 수행");
        List<CommunityByMemberResponse> communities = memberService.getCommunityListByMember(bearerToken);

        return ResponseEntity.status(HttpStatus.OK).body(communities);
    }
}
