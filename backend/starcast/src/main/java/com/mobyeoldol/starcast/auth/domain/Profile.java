package com.mobyeoldol.starcast.auth.domain;

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
public class Profile {

    @Id
    @Column(name = "profile_id", nullable = false)
    private String profileId;

    @OneToOne
    @JoinColumn(name = "auth_id", unique = true, nullable = false)
    private Auth authId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "nickname", nullable = false)
    private String nickname;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "profile_img", nullable = false)
    private String profileImg;

    @Column(name = "agreement_noti", nullable = false)
    private Boolean agreementNoti;

    @Column(name = "agreement_gps", nullable = false)
    private Boolean agreementGps;

    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted;
}
