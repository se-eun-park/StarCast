package com.mobyeoldol.starcast.place.domain;

import com.mobyeoldol.starcast.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Profile extends BaseTimeEntity {

    @Id
    @Column(name = "profile_uid", length = 36, nullable = false)
    private String profileUid;

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

    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Community> communities = new ArrayList<>();

    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reaction> reactions = new ArrayList<>();

    public void addCommunity(Community community) {
        this.communities.add(community);
        if (community.getProfile() != this) community.setProfile(this);
    }
}