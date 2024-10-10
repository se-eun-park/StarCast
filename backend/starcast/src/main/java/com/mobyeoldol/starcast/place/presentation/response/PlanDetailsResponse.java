package com.mobyeoldol.starcast.place.presentation.response;

import com.mobyeoldol.starcast.place.domain.enums.PlaceType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlanDetailsResponse {
    private String planUid;
    private Place place;
    private LocalDateTime dateTime;
    private Integer castarPoint;
    private Boolean isDeleted;

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Place{
        private String placeUid;
        private String name;
        private PlaceType type;
        private String image;
        private Address address;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Address{
        private String address1;
        private String address2;
        private String address3;
        private String address4;
    }

}
