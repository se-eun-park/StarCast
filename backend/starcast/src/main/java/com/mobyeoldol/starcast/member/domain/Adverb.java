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
public class Adverb {

    @Id
    @Column(name = "adverb_uid")
    private String adverbUid;

    @Column(name = "content")
    private String content;
}
