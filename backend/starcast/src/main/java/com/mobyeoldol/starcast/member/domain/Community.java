package com.mobyeoldol.starcast.member.domain;

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
public class Community {

    @Id
    @Column(name = "community_uid")
    private String communityUid;

    @ManyToOne
    @JoinColumn(name = "profile_uid", unique = true)
    private Profile profile;

    @ManyToOne
    @JoinColumn(name = "place_uid", unique = true)
    private Place place;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @CreationTimestamp
    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @UpdateTimestamp
    @Column(name = "modified_date")
    private LocalDateTime modifiedDate;

    @OneToOne
    @JoinColumn(name = "plan_uid", unique = true)
    private Plan plan;

    @Column(name = "is_deleted")
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
