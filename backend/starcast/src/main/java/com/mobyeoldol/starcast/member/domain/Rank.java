package com.mobyeoldol.starcast.member.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Rank {

    @Id
    @Column(name = "rank_uid", nullable = false)
    private String rankUid;

    @Column(name = "name", nullable = false)
    private String name;
}