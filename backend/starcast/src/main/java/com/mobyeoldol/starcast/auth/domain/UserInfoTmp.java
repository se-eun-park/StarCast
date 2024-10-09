package com.mobyeoldol.starcast.auth.domain;

import com.mobyeoldol.starcast.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "user_info_tmp")
public class UserInfoTmp extends BaseTimeEntity {

    @Id
    @Column(name = "user_info_tmp_uid", length = 36, nullable = false)
    private String userInfoTmpUid;

    @ManyToOne
    @JoinColumn(name = "auth_uid", nullable = false)
    private Auth auth;

    @Column(name = "email", length = 320, nullable = false)
    private String email;

    @Column(name = "name", length = 20, nullable = false)
    private String name;

    @Setter
    @Column(name = "nickname", length = 14)
    private String nickname;

    @Column(name = "profile_image_num", length = 20)
    private String profileImageNum;

    @Setter
    @Column(name = "consent_gps", length = 20)
    private boolean consentGps;

    @Setter
    @Column(name = "consent_notice", length = 20)
    private boolean consentNotice;


}
