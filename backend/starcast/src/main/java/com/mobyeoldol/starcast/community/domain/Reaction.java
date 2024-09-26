package com.mobyeoldol.starcast.community.domain;

import com.mobyeoldol.starcast.global.entity.BaseTimeEntity;
import com.mobyeoldol.starcast.member.domain.Profile;
import com.mobyeoldol.starcast.place.domain.enums.ReactionType;
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
public class Reaction extends BaseTimeEntity {

    @Id
    @Column(name = "reaction_uid", length = 36, nullable = false)
    private String reactionUid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_uid", nullable = false)
    private Profile profile;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "community_uid", nullable = false)
    private Community community;

    @Column(name = "reaction_type", length = 20, nullable = false)
    @Enumerated(EnumType.STRING)
    private ReactionType reactionType;
}
