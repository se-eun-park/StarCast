package com.mobyeoldol.starcast.member.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AddressDto {

    private String address1;
    private String address2;
    private String address3;
    private String address4;
}
