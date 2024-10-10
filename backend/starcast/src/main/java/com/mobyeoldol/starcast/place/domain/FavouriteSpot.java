package com.mobyeoldol.starcast.place.domain;

import com.mobyeoldol.starcast.global.entity.BaseTimeEntity;
import com.mobyeoldol.starcast.member.domain.Profile;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="favourite_spot")
public class FavouriteSpot extends BaseTimeEntity {

    @Id
    @Column(name = "favourite_spot_uid", length = 36, nullable = false)
    private String spotUid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_uid", nullable = false)
    private Profile profile;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_uid", nullable = false)
    private Place place;

    @Column(name = "is_deleted")
    private Boolean isDeleted;
}

