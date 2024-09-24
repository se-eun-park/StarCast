package com.mobyeoldol.starcast.member.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="community_image")
public class CommunityImage {

    @Id
    @Column(name = "image_uid")
    private String imageUid;

    @ManyToOne
    @JoinColumn(name = "community_uid", unique = true)
    private Community community;

    @Column(name = "url")
    private String url;

    @Column(name = "image_seq")
    private int imageSeq;

    @CreationTimestamp
    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @UpdateTimestamp
    @Column(name = "modified_date")
    private LocalDateTime modifiedDate;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    public void setCommunity(Community community) {
        this.community = community;
        if(!community.getCommunityImages().contains(this)) community.getCommunityImages().add(this);
    }
}
