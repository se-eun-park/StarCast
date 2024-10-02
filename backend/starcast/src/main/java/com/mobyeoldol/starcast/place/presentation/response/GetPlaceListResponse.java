package com.mobyeoldol.starcast.place.presentation.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class GetPlaceListResponse {
    private List<Data> dataList;

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Data{
        private String placeUid;
        private String image;
        private String name;
        private Address address;
        private Integer reviewCount;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Address{
        private String address1;
        private String address2;
        private String address3;
        private String address4;
    }
}
