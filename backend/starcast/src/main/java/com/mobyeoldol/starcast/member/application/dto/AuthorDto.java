package com.mobyeoldol.starcast.member.application.dto;

import com.mobyeoldol.starcast.member.domain.enums.CastarImage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AuthorDto {

    private String profileUid;
    private String nickname;
    private CastarImage profileImage;
}
