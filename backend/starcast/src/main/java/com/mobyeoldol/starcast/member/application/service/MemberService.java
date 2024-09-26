package com.mobyeoldol.starcast.member.application.service;

import com.mobyeoldol.starcast.member.presentation.request.UpdateMySpotRequest;
import com.mobyeoldol.starcast.member.presentation.response.*;
import jakarta.validation.Valid;

import java.util.List;

public interface MemberService {
    MyInfoResponse getMemberInfo(String bearerToken);
    List<CommunityByMemberResponse> getCommunityListByMember(String bearerToken);

    void updateMySpot(String profileUid, @Valid UpdateMySpotRequest request);
    void updateMyNickname(String profileUid, String nickname);
    void updateMyProfileImage(String profileUid, String image);
}