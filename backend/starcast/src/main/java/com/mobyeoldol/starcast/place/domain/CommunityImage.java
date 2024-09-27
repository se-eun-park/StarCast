package com.mobyeoldol.starcast.place.domain;

import com.mobyeoldol.starcast.global.entity.BaseTimeEntity;
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
@Table(name = "CommunityImage")
public class CommunityImage extends BaseTimeEntity {

    @Id
    @Column(name = "image_uid")
    private String imageUid;

    @Column(name = "url")
    private String url;

    @Column(name = "image_seq")
    private int imageSeq;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "community_uid")
    private Community community;

    public void setCommunity(Community community) {
        this.community = community;
        if(!community.getCommunityImages().contains(this)) community.getCommunityImages().add(this);
    }
}
