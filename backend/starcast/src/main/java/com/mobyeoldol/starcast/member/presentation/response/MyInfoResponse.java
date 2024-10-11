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
    private int myCurExp;
    private String rank;
    private Address address;

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Address {
        private String address1;
        private String address2;
        private String address3;
        private String address4;
    }
}