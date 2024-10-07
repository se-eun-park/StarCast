package com.mobyeoldol.starcast.calendar.presentation.response;

import com.mobyeoldol.starcast.calendar.domain.enums.MoonStatus;
import com.mobyeoldol.starcast.calendar.domain.enums.PrecipitationStatus;
import com.mobyeoldol.starcast.calendar.domain.enums.WeatherStatus;
import com.mobyeoldol.starcast.place.domain.enums.PlaceType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalTime;
import java.util.List;

@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CalendarMainResponse {
    private MyGPS myGPS;
    private MySpot mySpot;
    private List<FavouritePlace> favouritePlaceList;

    @Getter
    @SuperBuilder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Place {
        private String placeUid;
        private String address;
        private String details;
        private PlaceType placeType;
        private WeatherOfTheNight weatherOfTheNight;
        private BestDay best;
        private LocalTime moonSetTime;
        private IsPlanned isPlanned;
    }

    @Getter
    @SuperBuilder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class WeatherOfTheNight {
        private Hour hour21;
        private Hour hour22;
        private Hour hour23;
        private Hour hour00;
        private Hour hour01;
        private Hour hour02;
        private Hour hour03;
    }

    @Getter
    @SuperBuilder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BestDay {
        private String hour;
        private MoonStatus moonPhase;
        private Double lightPollution;
        private Integer humidity;
    }

    @Getter
    @SuperBuilder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Hour {
        private Double castarPoint;
        private WeatherStatus cloudCoverage;
        private PrecipitationStatus precipitation;
    }

    @Getter
    @SuperBuilder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class IsPlanned {
        private String isPlanned;
        private String hour;
    }

    @Getter
    @SuperBuilder
    public static class MyGPS extends Place {

    }

    @Getter
    @SuperBuilder
    public static class MySpot extends Place {

    }

    @Getter
    @SuperBuilder
    public static class FavouritePlace extends Place {

    }

}
