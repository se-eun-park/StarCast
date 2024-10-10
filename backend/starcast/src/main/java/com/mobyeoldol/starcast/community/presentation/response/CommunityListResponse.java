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
public class CommunityListResponse {
    private List<Communtiy> communtiyList;

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    static class Communtiy {
        private String communityUid;
        private String mainImage;
        private String title;
        private Author author;
        private Place place;
        private LocalDateTime dateTime;
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    static class Author {
        private String profileUid;
        private String nickname;
        private CastarImage profileImage;
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    static class Place {
        private String placeUid;
        private String addressSummary;
    }
}
