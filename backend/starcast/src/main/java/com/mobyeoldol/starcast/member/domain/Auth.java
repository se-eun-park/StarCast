package com.mobyeoldol.starcast.member.domain;

import com.mobyeoldol.starcast.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "auth")
public class Auth extends BaseTimeEntity {

    @Id
    @Column(name = "auth_uid")
    private String authId;

    @Column(name = "kakao_uid", nullable = false)
    private int kakaoUid;
}
