package com.mobyeoldol.starcast.member.presentation.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CustomErrorCode implements ErrorCode{

    PROFILE_INFO_NOT_FOUND(HttpStatus.NOT_FOUND, "프로필 정보가 존재하지 않습니다."),
    UPDATE_NICKNAME_FAILED(HttpStatus.NOT_FOUND, "닉네임 수정에 실패하였습니다."),
    UPDATE_PROFILE_IMAGE_FAILED(HttpStatus.NOT_FOUND, "프로필 이미지 수정에 실패하였습니다."),
    PLACE_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 장소입니다."),
    UPDATE_MYSPOT_FAILED(HttpStatus.NOT_FOUND, "내 주소 수정에 실패하였습니다.")
    ;

    public final HttpStatus httpStatus;
    public final String message;
}
