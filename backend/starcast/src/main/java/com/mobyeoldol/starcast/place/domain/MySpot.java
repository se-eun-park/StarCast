package com.mobyeoldol.starcast.place.domain;

import com.mobyeoldol.starcast.global.entity.BaseTimeEntity;
import com.mobyeoldol.starcast.member.domain.Profile;
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
@Table(name="my_spot")
public class MySpot extends BaseTimeEntity {

    @Id
    @Column(name = "my_spot_uid", length = 36, nullable = false)
    private String mySpotUid;

    @ManyToOne
    @JoinColumn(name = "profile_uid", unique = true)
    private Profile profile;

    @ManyToOne
    @JoinColumn(name = "place_uid", unique = true)
    private Place place;

    @Column(name = "is_deleted")
    private Boolean isDeleted;
}
