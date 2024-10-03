package com.mobyeoldol.starcast.place.presentation.response;

import com.mobyeoldol.starcast.place.domain.enums.PlaceType;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FavouriteSpotResponse {
    private String favouriteSpotId;
    private Place place;
    private LocalDateTime date;


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
