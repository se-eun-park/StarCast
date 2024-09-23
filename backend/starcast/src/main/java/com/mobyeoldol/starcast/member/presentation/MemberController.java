package com.mobyeoldol.starcast.member.presentation;

import com.mobyeoldol.starcast.member.application.service.MemberService;
import com.mobyeoldol.starcast.member.presentation.exception.CustomErrorCode;
import com.mobyeoldol.starcast.member.presentation.exception.CustomException;
import com.mobyeoldol.starcast.member.presentation.request.SaveNicknameRequest;
import com.mobyeoldol.starcast.member.presentation.response.MakeNewNicknameResponse;
import com.mobyeoldol.starcast.member.presentation.response.SaveNicknameResponse;
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

    @GetMapping("/make-nickname")
    public ResponseEntity<MakeNewNicknameResponse> makeNewNickname(@RequestHeader("Authorization") String bearerToken) {

        log.info("makeNewNickname controller 진입");
        MakeNewNicknameResponse nickname = memberService.makeNewNickname(bearerToken);

        return ResponseEntity.status(HttpStatus.OK).body(nickname);
    }

    @PostMapping("/save-nickname")
    public ResponseEntity<SaveNicknameResponse> saveNickname(@RequestHeader("Authorization") String bearerToken, @RequestBody SaveNicknameRequest request) {

        log.info("saveNickname controller 진입");
        SaveNicknameResponse savedNickname;
        if(request.getNickname() == null || request.getNickname().isEmpty()) throw new CustomException(CustomErrorCode.INVALID_NICKNAME_FORMAT);
        else {
            if(memberService.nicknameValidationCheck(request)) savedNickname = memberService.saveNickname(bearerToken, request);
            else throw new CustomException(CustomErrorCode.INVALID_NICKNAME_FORMAT);
        }

        return ResponseEntity.status(HttpStatus.OK).body(savedNickname);
    }
}
