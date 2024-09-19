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
public class FavouriteSpot {

    @Id
    @Column(name = "spot_uid")
    private String spotUid;

    @ManyToOne
    @JoinColumn(name = "profile_uid", unique = true)
    private Profile profile;

    @ManyToOne
    @JoinColumn(name = "place_uid", unique = true)
    private Place place;

    @Column(name = "spot_type")
    private String spotType;

    @Column(name = "date_time")
    private LocalDateTime dateTime;

    @Column(name = "castar_point")
    private int castarPoint;

    @CreationTimestamp
    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @UpdateTimestamp
    @Column(name = "modified_date")
    private LocalDateTime modifiedDate;

    @Column(name = "is_deleted")
    private Boolean isDeleted;
}
