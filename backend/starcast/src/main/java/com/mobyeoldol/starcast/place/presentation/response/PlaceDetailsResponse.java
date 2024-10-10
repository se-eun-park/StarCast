package com.mobyeoldol.starcast.place.presentation.response;

import com.mobyeoldol.starcast.place.domain.enums.PlaceType;
import com.mobyeoldol.starcast.place.domain.enums.ReactionType;
import lombok.*;

import java.util.List;
import java.util.Map;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlaceDetailsResponse {
    private String name;
    private PlaceType type;
    private String image;
    private Address address;
    private String websiteUrl;
    private String phoneNumber;
    private int reviewCount;
    private Map<ReactionType, List<Review>> topReviews;

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Address {
        private String address1;
        private String address2;
        private String address3;
        private String address4;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Review {
        private String image;
        private String title;
        private String content;
        private String date;
    }
}
