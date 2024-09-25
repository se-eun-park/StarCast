package com.mobyeoldol.starcast.member.domain;

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
@Table(name = "`rank`")
public class Rank {

    @Id
    @Column(name = "rank_uid", length = 36, nullable = false)
    private String rankUid;

    @Column(name = "name", length = 10, nullable = false)
    private String name;
}