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
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Profile extends BaseTimeEntity {

    @Id
    @Column(name = "profile_uid")
    private String profileUid;

    @OneToOne
    @JoinColumn(name = "auth_uid", unique = true, nullable = false)
    private Auth auth;

    @ManyToOne
    @JoinColumn(name = "rank_uid")
    private Rank rank;

    @Column(name = "email")
    private String email;

    @Column(name = "name")
    private String name;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "profile_image_num")
    private String profileImgNum;

    @Column(name = "exp")
    private int exp;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @Builder.Default
    @OneToMany(mappedBy = "profile")
    private List<Community> communities = new ArrayList<Community>();

    public void addCommunity(Community community) {
        this.communities.add(community);
        if (community.getProfile() != this) community.setProfile(this);
    }
}
