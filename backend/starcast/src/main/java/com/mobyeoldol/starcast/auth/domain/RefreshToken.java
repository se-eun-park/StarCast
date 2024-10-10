package com.mobyeoldol.starcast.auth.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@AllArgsConstructor
@Getter
@RedisHash(value = "refreshToken", timeToLive = 60 * 60 * 24) // TTL 1일
public class RefreshToken {

    @Id
    private String id;  // 유저를 식별할 고유 ID (예: kakaoUid)

    private String refreshToken;

    @Indexed
    private String accessToken;
}
