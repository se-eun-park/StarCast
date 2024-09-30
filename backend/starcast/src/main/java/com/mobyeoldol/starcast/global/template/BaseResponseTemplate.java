package com.mobyeoldol.starcast.global.template;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseResponseTemplate<T> {
    private int statusCode;
    private boolean success;
    private T data;
    private String errorMessage;

    public static <T> BaseResponseTemplate<T> success(T data) {
        BaseResponseTemplate<T> response = new BaseResponseTemplate<>();
        response.setStatusCode(200);
        response.setSuccess(true);
        response.setData(data);
        return response;
    }

    public static BaseResponseTemplate<?> failure(int statusCode, String errorMessage) {
        BaseResponseTemplate<?> response = new BaseResponseTemplate<>();
        response.setStatusCode(statusCode);
        response.setSuccess(false);
        response.setErrorMessage(errorMessage);
        return response;
    }
}

