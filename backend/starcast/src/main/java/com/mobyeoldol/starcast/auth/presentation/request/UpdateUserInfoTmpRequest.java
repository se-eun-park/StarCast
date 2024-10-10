package com.mobyeoldol.starcast.auth.presentation.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class UpdateUserInfoTmpRequest {
    private boolean consentGps;
    private boolean consentNotice;
}
