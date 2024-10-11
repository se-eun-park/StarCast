package com.mobyeoldol.starcast.community.presentation.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressResponse {
    List<Address> addressList;

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Address {
        private String placeUid;
        private String address1;
        private String address2;
        private String address3;
        private String address4;
        private String addressSummary;
    }
}
