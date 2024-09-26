package com.mobyeoldol.starcast.community.domain;

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
@Table(name="community_image")
public class CommunityImage extends BaseTimeEntity {

    @Id
    @Column(name = "image_uid", length = 36, nullable = false)
    private String imageUid;

    @ManyToOne
    @JoinColumn(name = "community_uid", unique = true)
    private Community community;

    @Column(name = "url", length = 2000, nullable = false)
    private String url;

    @Column(name = "image_seq", nullable = false)
    private int imageSeq;

    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted;

    public void setCommunity(Community community) {
        this.community = community;
        if(!community.getCommunityImages().contains(this)) community.getCommunityImages().add(this);
    }
}
