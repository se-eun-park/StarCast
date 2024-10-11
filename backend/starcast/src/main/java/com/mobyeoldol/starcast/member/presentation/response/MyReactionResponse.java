package com.mobyeoldol.starcast.member.presentation.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MyReactionResponse {

    private List<Reaction> reactions;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Reaction {
        private String reactionUid;
        private String type;
        private String date;
        private RelatedCommunity relatedCommunity;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class RelatedCommunity {
        private Community community;
        private Place place;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Community {
        private String communityUid;
        private String title;
        private String content;
        private String date;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Place {
        private String placeUid;
        private String name;
        private String type;
        private String image;
        private Address address;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Address {
        private String address1;
        private String address2;
        private String address3;
        private String address4;
    }
}
