package com.mobyeoldol.starcast.auth.presentation.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class UpdateUserInfoTmpResponse {

    private String userInfoTmpUid;
}
