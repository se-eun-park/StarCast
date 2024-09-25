package com.mobyeoldol.starcast.member.domain;

import com.mobyeoldol.starcast.global.entity.BaseTimeEntity;
import com.mobyeoldol.starcast.place.domain.Place;
import com.mobyeoldol.starcast.place.domain.Plan;
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
public class Community extends BaseTimeEntity {

    @Id
    @Column(name = "community_uid", length = 36, nullable = false)
    private String communityUid;

    @ManyToOne
    @JoinColumn(name = "profile_uid", unique = true)
    private Profile profile;

    @ManyToOne
    @JoinColumn(name = "place_uid", unique = true)
    private Place place;

    @Column(name = "title", length = 200, nullable = false)
    private String title;

    @Column(name = "content", length = 500, nullable = false)
    private String content;

    @OneToOne
    @JoinColumn(name = "plan_uid", unique = true)
    private Plan plan;

    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted;

    @OneToMany(mappedBy = "community")
    private List<CommunityImage> communityImages = new ArrayList<CommunityImage>();

    public void setProfile(Profile profile) {
        this.profile = profile;
        if(!profile.getCommunities().contains(this)) profile.getCommunities().add(this);
    }

    public void addCommunityImage(CommunityImage communityImage) {
        this.communityImages.add(communityImage);
        if (communityImage.getCommunity() != this) communityImage.setCommunity(this);
    }
}
