package com.mobyeoldol.starcast.member.application.service;

import com.mobyeoldol.starcast.member.presentation.request.UpdateMySpotRequest;
import com.mobyeoldol.starcast.member.presentation.response.UpdateMySpotResponse;
import com.mobyeoldol.starcast.member.presentation.response.UpdateMyNicknameResponse;
import com.mobyeoldol.starcast.member.presentation.response.UpdateMyProfileImageResponse;

public interface MemberService {
    UpdateMyNicknameResponse updateMyNickname(String bearerToken, String nickname);
    UpdateMyProfileImageResponse UpdateMyProfileImage(String bearerToken, String file);
    UpdateMySpotResponse UpdateMySpot(String bearerToken, UpdateMySpotRequest request);
}