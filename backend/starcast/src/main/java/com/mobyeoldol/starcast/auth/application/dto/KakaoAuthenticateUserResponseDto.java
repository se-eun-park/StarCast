package com.mobyeoldol.starcast.auth.application.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class KakaoAuthenticateUserResponseDto {

    //회원 번호
    @JsonProperty("id")
    public Long id;

    @JsonProperty("expires_in")
    public Long expiresIn;

    @JsonProperty("app_id")
    public Long appId;
}
