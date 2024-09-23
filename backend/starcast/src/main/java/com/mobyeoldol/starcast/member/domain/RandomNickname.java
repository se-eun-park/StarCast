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
public class RandomNickname {

    @Id
    @Column(name = "random_nickname_uid")
    private String randomNicknameUid;

    @ManyToOne
    @JoinColumn(name = "adverb_uid")
    private Adverb adverb;

    @ManyToOne
    @JoinColumn(name = "adjective_uid")
    private Adjective adjective;

    @Column(name = "sequence")
    private int sequence;
}
