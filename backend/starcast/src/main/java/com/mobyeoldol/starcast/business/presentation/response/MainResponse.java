package com.mobyeoldol.starcast.business.presentation.response;

import com.mobyeoldol.starcast.calendar.domain.enums.LightPolluitonStatus;
import com.mobyeoldol.starcast.calendar.domain.enums.MoonStatus;
import com.mobyeoldol.starcast.calendar.domain.enums.WeatherStatus;
import lombok.*;


import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class MainResponse {
    private MySpot mySpot;
    private Gps gps;
    private Plan plan;

    @Getter
    @Setter
    @SuperBuilder
    public static class MySpot extends Place {
    }

    @Getter
    @Setter
    @SuperBuilder
    public static class Gps extends Place {
    }

    @Getter
    @Setter
    @SuperBuilder
    public static class Plan extends Place {
    }
}

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
class Place {
    private String name;
    private int casterPoint;
    private MoonStatus moonPhase;
    private WeatherStatus cloudCoverage;
    private LightPolluitonStatus lightPollution;
    private BigDecimal humidity;
    private String moonSetTime;
}



