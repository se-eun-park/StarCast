package com.mobyeoldol.starcast.member.domain;

import com.mobyeoldol.starcast.auth.domain.Auth;
import com.mobyeoldol.starcast.community.domain.Community;
import com.mobyeoldol.starcast.community.domain.Reaction;
import com.mobyeoldol.starcast.global.entity.BaseTimeEntity;
import com.mobyeoldol.starcast.member.domain.enums.CastarImage;
import com.mobyeoldol.starcast.notice.domain.Consent;
import com.mobyeoldol.starcast.notice.domain.Notice;
import com.mobyeoldol.starcast.place.domain.FavouriteSpot;
import com.mobyeoldol.starcast.place.domain.MySpot;
import com.mobyeoldol.starcast.place.domain.Plan;
import com.mobyeoldol.starcast.place.domain.enums.MainPlace;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "profile")
public class Profile extends BaseTimeEntity {

    @Id
    @Column(name = "profile_uid", length = 36, nullable = false)
    private String profileUid;

    @OneToOne
    @JoinColumn(name = "auth_uid", unique = true, nullable = false)
    private Auth auth;

    @ManyToOne
    @JoinColumn(name = "rank_uid", nullable = false)
    private Rank rank;

    @Column(name = "email", length = 320, nullable = false)
    private String email;

    @Column(name = "name", length = 20, nullable = false)
    private String name;

    @Column(name = "nickname", length = 14, nullable = false)
    private String nickname;

    @Column(name = "profile_image_num", length = 20, nullable = false)
    @Enumerated(EnumType.STRING)
    private CastarImage profileImgNum;

    @Column(name = "exp", nullable = false)
    private int exp;

    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted;

    @Enumerated(EnumType.STRING)
    @Column(name = "action_place_type")
    private MainPlace actionPlaceType;

    @Builder.Default
    @OneToMany(mappedBy = "profile", fetch = FetchType.LAZY)
    private List<Community> communities = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "profile", fetch = FetchType.LAZY)
    private List<Reaction> reactions = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "profile", fetch = FetchType.LAZY)
    private List<Notice> notices = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "profile", fetch = FetchType.LAZY)
    private List<Consent> consents = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "profile", fetch = FetchType.LAZY)
    private List<Plan> plans = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "profile", fetch = FetchType.LAZY)
    private List<MySpot> mySpots = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "profile", fetch = FetchType.LAZY)
    private List<FavouriteSpot> favouriteSpots = new ArrayList<>();


    public void addCommunity(Community community) {
        this.communities.add(community);
        if (community.getProfile() != this) community.setProfile(this);
    }
}
