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
public class Plan {

    @Id
    @Column(name = "plan_uid")
    private String planUid;

    @ManyToOne
    @JoinColumn(name = "profile_uid", unique = true)
    private Profile profile;

    @ManyToOne
    @JoinColumn(name = "place_uid", unique = true)
    private Place place;

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
