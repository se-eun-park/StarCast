package com.mobyeoldol.starcast.member.presentation.response;

import com.mobyeoldol.starcast.member.application.dto.AddressDto;
import com.mobyeoldol.starcast.member.application.dto.AuthorDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommunityByMemberResponse {

    private String communityUid;
    private String mainImage;
    private String title;
    private AuthorDto author;
    private LocalDateTime date_time;
    private AddressDto address;
    private int castarPoint;
}




