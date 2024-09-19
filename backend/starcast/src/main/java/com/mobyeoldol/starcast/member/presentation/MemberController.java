package com.mobyeoldol.starcast.member.presentation;

import com.mobyeoldol.starcast.member.application.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/member")
public class MemberController {

    private final MemberService memberService;

//    @GetMapping("/info")
//    public ResponseEntity<?> getMyInfo(@RequestHeader("Authorization") String accessToken) {
//        log.info("updateInfo 진입");
//        getMyInfo(accessToken);
//
//
//        return new ResponseEntity<>(userInfo, HttpStatus.OK);
//    }
}
