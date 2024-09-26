package com.mobyeoldol.starcast.member.presentation.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MyInfoResponse {

    private String name;
    private String nickname;
    private String email;
    private String profileImage;
    private String address;
    private int myCurExp;
    private String rank;
}