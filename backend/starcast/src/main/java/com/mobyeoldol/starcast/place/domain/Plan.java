package com.mobyeoldol.starcast.place.domain;

import com.mobyeoldol.starcast.global.entity.BaseTimeEntity;
import com.mobyeoldol.starcast.member.domain.Profile;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "plan")
public class Plan extends BaseTimeEntity {

    @Id
    @Column(name = "plan_uid", length = 36, nullable = false)
    private String planUid;

    @ManyToOne
    @JoinColumn(name = "profile_uid")
    private Profile profile;

    @ManyToOne
    @JoinColumn(name = "place_uid")
    private Place place;

    @Column(name = "date_time", length = 15, nullable = false)
    private LocalDateTime dateTime;

    @Column(name = "castar_point", nullable = false)
    private int castarPoint;

    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted;
}
