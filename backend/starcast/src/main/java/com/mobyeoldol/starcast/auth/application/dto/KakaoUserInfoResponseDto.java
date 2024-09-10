package com.mobyeoldol.starcast.auth.application.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class KakaoUserInfoResponseDto {

    //회원 번호
    @JsonProperty("id")
    public Long id;

    @JsonProperty("KakaoAccount")
    private KakaoAccount kakaoAccount;
}

class KakaoAccount {

    @JsonProperty("profile_needs_agreement")
    public Boolean profile_needs_agreement;

    @JsonProperty("profile_nickname_needs_agreement")
    public Boolean profile_nickname_needs_agreement;

    @JsonProperty("profile")
    private Profile profile;

    @JsonProperty("email_needs_agreement")
    public Boolean email_needs_agreement;

    @JsonProperty("email")
    public String email;
}

class Profile {

    @JsonProperty("nickname")
    public String nickname;
}
