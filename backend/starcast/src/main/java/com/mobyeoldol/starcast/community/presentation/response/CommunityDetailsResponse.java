package com.mobyeoldol.starcast.community.presentation.response;

import com.mobyeoldol.starcast.member.domain.enums.CastarImage;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommunityDetailsResponse {
    private String communityUid;
    private List<String> images;
    private String title;
    private String content;
    private Author author;
    private LocalDateTime dateTime;
    private Place place;
    private Reaction reaction;

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Author {
        private String profileUid;
        private String nickname;
        private CastarImage profileImage;
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Place {
        private String placeUid;
        private String addressSummary;
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Reaction {
        private boolean visitAgain;
        private boolean helpful;
        private boolean nicePhotos;
    }
}
