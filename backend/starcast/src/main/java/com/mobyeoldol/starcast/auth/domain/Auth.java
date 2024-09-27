package com.mobyeoldol.starcast.auth.domain;

import com.mobyeoldol.starcast.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "auth")
public class Auth extends BaseTimeEntity {

    @Id
    @Column(name = "auth_uid", length = 36, nullable = false)
    private String authId;

    @Column(name = "kakao_uid", unique = true, nullable = false)
    private int kakaoUid;
}
