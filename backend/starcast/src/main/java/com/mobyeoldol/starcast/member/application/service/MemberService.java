package com.mobyeoldol.starcast.member.application.service;

import com.mobyeoldol.starcast.member.presentation.request.SaveNicknameRequest;
import com.mobyeoldol.starcast.member.presentation.response.MakeNewNicknameResponse;
import com.mobyeoldol.starcast.member.presentation.response.SaveNicknameResponse;

public interface MemberService {
    MakeNewNicknameResponse makeNewNickname(String bearerToken);
    SaveNicknameResponse saveNickname(String bearerToken, SaveNicknameRequest request);
    boolean nicknameValidationCheck(SaveNicknameRequest request);
}