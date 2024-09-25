package com.mobyeoldol.starcast.place.domain;

import com.mobyeoldol.starcast.global.entity.BaseTimeEntity;
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

    @Column(name = "profile_uid", length = 36, nullable = false)
    private String profileUid;

    @Column(name = "place_uid", length = 36, nullable = false)
    private String placeUid;

    @Column(name = "is_deleted")
    private Boolean isDeleted;
}

