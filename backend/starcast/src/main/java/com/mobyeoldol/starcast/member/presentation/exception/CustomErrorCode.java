package com.mobyeoldol.starcast.member.presentation.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CustomErrorCode implements ErrorCode{

    PROFILE_INFO_NOT_FOUND(HttpStatus.NOT_FOUND, "유저 정보가 존재하지 않습니다."),
    ADVERB_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 아이디의 부사가 존재하지 않습니다."),
    ADJECTIVE_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 아이디의 형용사가 존재하지 않습니다."),
    INVALID_NICKNAME_FORMAT(HttpStatus.BAD_REQUEST, "유효하지 않은 닉네임 형식입니다."),
    SAVE_NICKNAME_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "닉네임 저장에 실패하였습니다.")
    ;

    public final HttpStatus httpStatus;
    public final String message;
}
