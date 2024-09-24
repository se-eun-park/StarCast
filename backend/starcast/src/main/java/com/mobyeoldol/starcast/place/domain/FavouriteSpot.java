package com.mobyeoldol.starcast.place.domain;

import com.mobyeoldol.starcast.global.entity.BaseTimeEntity;
import com.mobyeoldol.starcast.place.domain.enums.SpotType;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FavouriteSpot extends BaseTimeEntity {

    @Id
    @Column(name = "spot_uid", length = 36, nullable = false)
    private String spotUid;

    @Column(name = "profile_uid", length = 36, nullable = false)
    private String profileUid;

    @Column(name = "place_uid", length = 36, nullable = false)
    private String placeUid;

    @Column(name = "spot_type", length = 10, nullable = false)
    private SpotType spotType;

    @Column(name = "date_time")
    private LocalDateTime dateTime;

    @Column(name = "castar_point", nullable = false)
    private int castarPoint;

    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted;
}

