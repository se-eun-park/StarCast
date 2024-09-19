package com.mobyeoldol.starcast.member.presentation.response;

import com.mobyeoldol.starcast.member.domain.Profile;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommunityByMemberResponse {

    private String communityUid;
    private String mainImage;
    private String title;
    private Profile author;
    private String email;
    private String profileImage;
    private String address;
    private int myCurExp;
    private String rank;
}