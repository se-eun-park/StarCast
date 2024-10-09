package com.mobyeoldol.starcast.auth.presentation.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor // 기본 생성자 추가
public class LogoutResponse {

    @JsonProperty("id") // 필요 시 명시적으로 JSON 속성과 매핑
    private long id;
}
