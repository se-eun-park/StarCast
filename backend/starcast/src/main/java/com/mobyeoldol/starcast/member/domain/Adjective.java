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
public class Adjective {

    @Id
    @Column(name = "adjective_uid")
    private String adjectiveUid;

    @Column(name = "content")
    private String content;
}
