package com.mobyeoldol.starcast.business.presentation.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MainRequest {
    @NotBlank(message = "주소1은 필수입니다.")
    private String address1;
    private String address2;
    private String address3;
    @NotBlank(message = "주소4는 필수입니다.")
    private String address4;
}
