package com.mobyeoldol.starcast.member.domain;

import com.mobyeoldol.starcast.auth.domain.Auth;
import com.mobyeoldol.starcast.community.domain.Community;
import com.mobyeoldol.starcast.global.entity.BaseTimeEntity;
import com.mobyeoldol.starcast.notice.domain.Notice;
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
    private String profileImgNum;

    @Column(name = "exp", nullable = false)
    private int exp;

    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted;

    @OneToMany(mappedBy = "profile")
    private List<Community> communities = new ArrayList<>();

    @OneToMany(mappedBy = "profile")
    private List<Notice> notices = new ArrayList<>();


    public void addCommunity(Community community) {
        this.communities.add(community);
        if (community.getProfile() != this) community.setProfile(this);
    }
}
