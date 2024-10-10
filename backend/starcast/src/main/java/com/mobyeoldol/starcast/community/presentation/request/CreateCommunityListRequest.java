package com.mobyeoldol.starcast.community.presentation.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateCommunityListRequest {

    private List<Image> images;
    @NotBlank(message = "제목은 필수입니다.")
    private String title;
    @NotBlank(message = "내용은 필수입니다.")
    private String content;
    private Place place;

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Image {
        private String image;
    }

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
