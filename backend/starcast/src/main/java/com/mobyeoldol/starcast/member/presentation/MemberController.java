package com.mobyeoldol.starcast.member.presentation;

import com.mobyeoldol.starcast.member.application.service.MemberService;
import com.mobyeoldol.starcast.member.presentation.request.UpdateMySpotRequest;
import com.mobyeoldol.starcast.member.presentation.response.UpdateMyNicknameResponse;
import com.mobyeoldol.starcast.member.presentation.response.UpdateMyProfileImageResponse;
import com.mobyeoldol.starcast.member.presentation.response.UpdateMySpotResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/member")
public class MemberController {

    private final MemberService memberService;

    @PatchMapping("/update-nickname")
    public ResponseEntity<UpdateMyNicknameResponse> updateMyNickname(@RequestHeader("Authorization") String bearerToken, @RequestBody String nickname) {
        log.info("updateMyNickname controller 진입");
        UpdateMyNicknameResponse myInfo = memberService.updateMyNickname(bearerToken, nickname);
        return ResponseEntity.status(HttpStatus.OK).body(myInfo);
    }

    @PatchMapping("/update-profile-image")
    public ResponseEntity<UpdateMyProfileImageResponse> updateMyProfileImage(@RequestHeader("Authorization") String bearerToken, @RequestBody String image) {
        log.info("updateMyProfileImage controller 진입");
        UpdateMyProfileImageResponse myInfo = memberService.UpdateMyProfileImage(bearerToken, image);
        return ResponseEntity.status(HttpStatus.OK).body(myInfo);
    }

    @PatchMapping("/update-address")
    public ResponseEntity<UpdateMySpotResponse> updateMySpot(@RequestHeader("Authorization") String bearerToken, @RequestBody UpdateMySpotRequest request) {
        log.info("updateMySpot controller 진입");
        UpdateMySpotResponse myInfo = memberService.UpdateMySpot(bearerToken, request);
        return ResponseEntity.status(HttpStatus.OK).body(myInfo);
    }
}
