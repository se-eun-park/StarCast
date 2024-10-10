package com.mobyeoldol.starcast.member.presentation.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateMySpotRequest {

    @NotBlank
    private String address1;

    private String address2;

    private String address3;

    @NotBlank
    private String address4;
}
