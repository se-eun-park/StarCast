package com.mobyeoldol.starcast.calendar.presentation.response;

import com.mobyeoldol.starcast.calendar.domain.enums.MoonStatus;
import com.mobyeoldol.starcast.calendar.domain.enums.PrecipitationStatus;
import com.mobyeoldol.starcast.calendar.domain.enums.WeatherStatus;
import com.mobyeoldol.starcast.place.domain.enums.PlaceType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CalendarMainResponse {
    private MyGPS myGPS;
    private MySpot mySpot;
    private List<FavouritePlace> favouritePlaceList;

    static class Place {
        private String placeUid;
        private String address;
        private String details;
        private PlaceType placeType;
        private WeatherOfTheNight weatherOfTheNight;
        private BestDay best;
        private LocalTime moonSetTime;
        private IsPlanned isPlanned;
    }

    static class WeatherOfTheNight {
        private Hour hour21;
        private Hour hour22;
        private Hour hour23;
        private Hour hour00;
        private Hour hour01;
        private Hour hour02;
        private Hour hour03;
    }

    static class BestDay {
        private String hour;
        private MoonStatus moonPhase;
        private Double lightPollution;
        private Integer humidity;
    }

    static class Hour {
        private Double castarPoint;
        private WeatherStatus cloudCoverage;
        private PrecipitationStatus precipitation;
    }

    static class IsPlanned {
        private String isPlanned;
        private String hour;
    }


    static class MyGPS extends Place{

    }

    static class MySpot extends Place{

    }

    static class FavouritePlace extends Place{

    }

}
