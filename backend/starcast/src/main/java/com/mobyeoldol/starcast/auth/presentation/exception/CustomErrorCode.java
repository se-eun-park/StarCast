package com.mobyeoldol.starcast.auth.presentation.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CustomErrorCode implements ErrorCode{

    AUTH_NOT_CREATED(HttpStatus.BAD_REQUEST, "Auth 생성에 실패하였습니다."),
    USER_INFO_NOT_FOUND(HttpStatus.BAD_REQUEST, "사용자 정보가 존재하지 않습니다.");
    ;

    public final HttpStatus httpStatus;
    public final String message;
}
