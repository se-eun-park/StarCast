package com.mobyeoldol.starcast.member.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AuthorDto {

    private String profile_uid;
    private String nickname;
    private String profileImage;
}
