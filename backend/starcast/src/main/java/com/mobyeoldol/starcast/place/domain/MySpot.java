package com.mobyeoldol.starcast.place.domain;

import com.mobyeoldol.starcast.global.entity.BaseTimeEntity;
import com.mobyeoldol.starcast.member.domain.Profile;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="my_spot")
public class MySpot extends BaseTimeEntity {

    @Id
    @Column(name = "my_spot_uid", length = 36, nullable = false)
    private String mySpotUid;

    @ManyToOne
    @JoinColumn(name = "profile_uid")
    private Profile profile;

    @ManyToOne
    @JoinColumn(name = "place_uid")
    private Place place;
}
