package com.mobyeoldol.starcast.auth.application.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor //역직렬화를 위한 기본 생성자
@JsonIgnoreProperties(ignoreUnknown = true)
public class KakaoTokenInfoResponseDto {

    @JsonProperty("id")
    private long id;

    @JsonProperty("expires_in")
    private int expiresIn;

    @JsonProperty("app_id")
    private int appId;
}

