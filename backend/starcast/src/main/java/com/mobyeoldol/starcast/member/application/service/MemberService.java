package com.mobyeoldol.starcast.member.application.service;

import com.mobyeoldol.starcast.member.presentation.response.CommunityByMemberResponse;
import com.mobyeoldol.starcast.member.presentation.response.MyInfoResponse;

import java.util.List;

public interface MemberService {
    MyInfoResponse getMemberInfo(String bearerToken);
    List<CommunityByMemberResponse> getCommunityListByMember(String bearerToken);
}