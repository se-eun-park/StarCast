package com.mobyeoldol.starcast.notice.domain;

import com.mobyeoldol.starcast.global.entity.BaseTimeEntity;
import com.mobyeoldol.starcast.member.domain.Profile;
import com.mobyeoldol.starcast.notice.domain.enums.ConsentType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Consent extends BaseTimeEntity {
    @Id
    @Column(name = "consent_uid", nullable = false)
    private String consentUid;

    @ManyToOne
    @JoinColumn(name = "profile_uid", nullable = false)
    private Profile profile;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private ConsentType type;

    @Column(name = "flag", nullable = false)
    private boolean flag;
}
