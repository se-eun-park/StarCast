package com.mobyeoldol.starcast.member.application.service;

import com.mobyeoldol.starcast.member.presentation.response.MyInfoResponse;

public interface MemberService {
    MyInfoResponse getMemberInfo(String bearerToken);
//    List<CommunityByMemberResponse> getCommunityListByMember(String bearerToken);
}