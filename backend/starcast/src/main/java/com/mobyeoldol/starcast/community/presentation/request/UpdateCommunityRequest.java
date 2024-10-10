package com.mobyeoldol.starcast.community.presentation.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCommunityRequest {
    @NotBlank(message = "커뮤니티 아이디는 필수입니다.")
    private String communityUid;
    private List<String> images;
    private String title;
    private String content;
    private Place place;

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Place {
        private String placeUid;
        private Address address;
    }

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
