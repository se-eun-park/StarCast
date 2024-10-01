package com.mobyeoldol.starcast.global.exception;

import com.mobyeoldol.starcast.global.template.BaseResponseTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<BaseResponseTemplate<?>> handleAllExceptions(Exception ex, WebRequest request) {
        ex.printStackTrace();

        BaseResponseTemplate<?> errorResponse = BaseResponseTemplate.failure(
                HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    // IllegalArgumentException 등 특정 예외 처리
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<BaseResponseTemplate<?>> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {
        BaseResponseTemplate<?> errorResponse = BaseResponseTemplate.failure(
                HttpStatus.BAD_REQUEST.value(), ex.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
}
