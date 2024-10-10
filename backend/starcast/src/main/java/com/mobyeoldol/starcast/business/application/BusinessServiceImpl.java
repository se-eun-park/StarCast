package com.mobyeoldol.starcast.business.application;

import com.mobyeoldol.starcast.business.presentation.request.MainRequest;
import com.mobyeoldol.starcast.business.presentation.response.MainResponse;
import com.mobyeoldol.starcast.calendar.domain.Forecast;
import com.mobyeoldol.starcast.calendar.domain.LunarCycle;
import com.mobyeoldol.starcast.calendar.domain.MoonriseMoonsetTimes;
import com.mobyeoldol.starcast.calendar.domain.enums.LightPolluitonStatus;
import com.mobyeoldol.starcast.calendar.domain.enums.MoonStatus;
import com.mobyeoldol.starcast.calendar.domain.enums.WeatherStatus;
import com.mobyeoldol.starcast.calendar.domain.repository.ForecastRepository;
import com.mobyeoldol.starcast.calendar.domain.repository.LunarCycleRepository;
import com.mobyeoldol.starcast.calendar.domain.repository.MoonriseMoonsetTimesRepository;
import com.mobyeoldol.starcast.place.domain.MySpot;
import com.mobyeoldol.starcast.place.domain.Place;
import com.mobyeoldol.starcast.place.domain.Plan;
import com.mobyeoldol.starcast.place.domain.repository.MySpotRepository;
import com.mobyeoldol.starcast.place.domain.repository.PlaceRepository;
import com.mobyeoldol.starcast.place.domain.repository.PlanRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class BusinessServiceImpl implements BusinessService {
    private final MySpotRepository mySpotRepository;
    private final PlaceRepository placeRepository;
    private final PlanRepository planRepository;
    private final LunarCycleRepository lunarCycleRepository;
    private final MoonriseMoonsetTimesRepository moonriseMoonsetTimesRepository;
    private final ForecastRepository forecastRepository;

    @Override
    public MainResponse getMain(String profileUid, MainRequest request) {

        WeatherStatus ws = null;
        LightPolluitonStatus lps = null;
        BigDecimal hu = null;
        MoonStatus ms = null;

        log.info("[메인(내 장소, GPS)에 대해 캐스타 점수 불러오기] 1. GPS 가져오기");
        Place gpsPlace = placeRepository.findByAddress1AndAddress2AndAddress3AndAddress4(
                request.getAddress1(),
                request.getAddress2(),
                request.getAddress3(),
                request.getAddress4()
        ).orElseThrow(() -> new EntityNotFoundException("GPS 주소를 찾을 수 없습니다."));

        ms = getMoonPhase(LocalDate.now());
        ws = getCloudCoverage(gpsPlace.getPlaceUid(), LocalDate.now());
        lps = getLightPollution(gpsPlace.getLightPollution());
        hu = getHumidity(gpsPlace.getPlaceUid(), LocalDate.now());

        MainResponse.Gps gpsResponse = MainResponse.Gps.builder()
                .name(gpsPlace.getAddress4())
                .moonPhase(ms)
                .cloudCoverage(ws)
                .lightPollution(lps)
                .humidity(hu)
                .moonSetTime(getMoonSetTime(gpsPlace.getPlaceUid(), LocalDate.now()))
                .casterPoint(getCastarPoint(ws, lps, hu, ms))
                .build();


        log.info("[메인(내 장소, GPS)에 대해 캐스타 점수 불러오기] 2. MySpot 가져오기");
        Optional<MySpot> mySpotOptional = mySpotRepository.findByProfile_ProfileUid(profileUid);
        MainResponse.MySpot mySpotResponse = null;
        if (mySpotOptional.isPresent()) {
            MySpot mySpot = mySpotOptional.get();

            ms = getMoonPhase(LocalDate.now());
            ws = getCloudCoverage(mySpot.getPlace().getPlaceUid(), LocalDate.now());
            lps = getLightPollution(mySpot.getPlace().getLightPollution());
            hu = getHumidity(mySpot.getPlace().getPlaceUid(), LocalDate.now());

            mySpotResponse = MainResponse.MySpot.builder()
                    .name(mySpot.getPlace().getAddress4())
                    .moonPhase(ms)
                    .cloudCoverage(ws)
                    .lightPollution(lps)
                    .humidity(hu)
                    .moonSetTime(getMoonSetTime(mySpot.getPlace().getPlaceUid(), LocalDate.now()))
                    .casterPoint(getCastarPoint(ws, lps, hu, ms))
                    .build();
        }


        log.info("[메인(내 장소, GPS)에 대해 캐스타 점수 불러오기] 3. 찜 가져오기");
        List<Plan> planList = planRepository.findByProfile_ProfileUidAndIsDeletedFalse(profileUid);
        MainResponse.Plan planResponse = null;
        if(!planList.isEmpty()) {
            Plan plan = planList.get(0);

            ms = getMoonPhase(LocalDate.from(plan.getDateTime()));
            ws = getCloudCoverage(plan.getPlace().getPlaceUid(), LocalDate.from(plan.getDateTime()));
            lps = getLightPollution(plan.getPlace().getLightPollution());
            hu = getHumidity(plan.getPlace().getPlaceUid(), LocalDate.from(plan.getDateTime()));

            planResponse = MainResponse.Plan.builder()
                    .name(plan.getPlace().getAddress4())
                    .moonPhase(ms)
                    .cloudCoverage(ws)
                    .lightPollution(lps)
                    .humidity(hu)
                    .moonSetTime(getMoonSetTime(plan.getPlace().getPlaceUid(), LocalDate.from(plan.getDateTime())))
                    .casterPoint(getCastarPoint(ws, lps, hu, ms))
                    .build();
        }

        return MainResponse.builder()
                .mySpot(mySpotResponse)
                .gps(gpsResponse)
                .plan(planResponse)
                .build();
    }

    private int getCastarPoint(WeatherStatus ws, LightPolluitonStatus lps, BigDecimal hu, MoonStatus ms) {
        double weatherFactor = switch (ws) {
            case CLEAR -> 1.0;
            case PARTLY_CLOUD -> 0.25;
            case CLOUDY -> 0.0;
        };

        double lightPollutionFactor = switch (lps) {
            case LOW -> 1.0;
            case MEDIUM -> 0.75;
            case HIGH -> 0.5;
        };

        double moonPhaseFactor = switch (ms) {
            case DARK_MOON -> 1.0;
            case MOON -> 0.75;
            case BRIGHT_MOON -> 0.5;
        };

        // 캐스타 점수 계산: (ws * lps * ms) * 100 - hu
        double castarPoint = (weatherFactor * lightPollutionFactor * moonPhaseFactor) * 100 - hu.doubleValue()/10;

        return (int) castarPoint;
    }

    private LightPolluitonStatus getLightPollution(BigDecimal lightPollution) {
        if (lightPollution.compareTo(BigDecimal.valueOf(50)) < 0) {
            return LightPolluitonStatus.LOW;
        } else if (lightPollution.compareTo(BigDecimal.valueOf(100)) < 0) {
            return LightPolluitonStatus.MEDIUM;
        } else {
            return LightPolluitonStatus.HIGH;
        }
    }



    private WeatherStatus getCloudCoverage(String placeUid, LocalDate date) {
        String forecastUid = date.format(DateTimeFormatter.ofPattern("yyyy_MM_dd"))+"_"+placeUid;
        log.info("1. forecastUid : {} [getCloudCoverage]", forecastUid);

        log.info("2. forecastUid로 습도 조회");
        Forecast forecast = forecastRepository.findByForecastUid(forecastUid)
                .orElseThrow(() -> new EntityNotFoundException("해당 날짜의 단중기예보를 찾을 수 없습니다."));

        Integer totalSky = forecast.getSky00()
                +forecast.getSky00()
                +forecast.getSky01()
                +forecast.getSky02()
                +forecast.getSky21()
                +forecast.getSky22()
                +forecast.getSky23();
        totalSky/=6;

        switch (totalSky) {
            case 1:
                return WeatherStatus.CLEAR;
            case 2:
                return WeatherStatus.PARTLY_CLOUD;
            default:
                return WeatherStatus.CLOUDY;
        }
    }

    private BigDecimal getHumidity(String placeUid, LocalDate date) {
        String forecastUid = date.format(DateTimeFormatter.ofPattern("yyyy_MM_dd"))+"_"+placeUid;
        log.info("1. forecastUid : {} [getHumidity]", forecastUid);

        log.info("2. forecastUid로 습도 조회");
        Forecast forecast = forecastRepository.findByForecastUid(forecastUid)
                .orElseThrow(() -> new EntityNotFoundException("해당 날짜의 단중기예보를 찾을 수 없습니다."));

        BigDecimal totalHumidity = forecast.getHumidity00()
                .add(forecast.getHumidity01())
                .add(forecast.getHumidity02())
                .add(forecast.getHumidity21())
                .add(forecast.getHumidity22())
                .add(forecast.getHumidity23());

        return totalHumidity.divide(BigDecimal.valueOf(6), 2, RoundingMode.HALF_UP);
    }

    private String getMoonSetTime(String placeUid, LocalDate date) {
        String moonRiseSetTimeUid = date.format(DateTimeFormatter.ofPattern("yyyy_MM_dd"))+"_"+placeUid;
        log.info("1. moonRiseSetTimeUid : {} [getMoonSetTime]", moonRiseSetTimeUid);

        log.info("2. moon_rise_set_time_uid로 월령 값 조회");
        MoonriseMoonsetTimes moonriseMoonsetTimes = moonriseMoonsetTimesRepository.findByMoonRiseSetTimeUid(moonRiseSetTimeUid)
                .orElseThrow(() -> new EntityNotFoundException("해당 날짜의 위치별 출몰시각을 찾을 수 없습니다."));

        return String.format("%02d:%02d", moonriseMoonsetTimes.getMoonsetHour(), moonriseMoonsetTimes.getMoonsetMin());
    }

    private MoonStatus getMoonPhase(LocalDate date) {
        log.info("1. 오늘 날짜로 lunar_cycle_uid 생성");
        String lunarCycleUid = date.format(DateTimeFormatter.ofPattern("yyyy_MM_dd"));
        log.info("1. lunarCycleUid : {}", lunarCycleUid);

        log.info("2. lunar_cycle_uid로 월령 값 조회");
        LunarCycle lunarCycle = lunarCycleRepository.findByLunarCycleUid(lunarCycleUid)
                .orElseThrow(() -> new EntityNotFoundException("해당 날짜의 월령 값을 찾을 수 없습니다."));

        BigDecimal lunAge = lunarCycle.getLunarAge();

        log.info("3. lunAge 값에 따라 MoonStatus 반환");
        if (isBetween(lunAge, 0, 5) || isBetween(lunAge, 25, 30)) {
            return MoonStatus.DARK_MOON;
        } else if (isBetween(lunAge, 5, 10) || isBetween(lunAge, 20, 25)) {
            return MoonStatus.MOON;
        } else if (isBetween(lunAge, 10, 20)) {
            return MoonStatus.BRIGHT_MOON;
        } else {
            throw new IllegalStateException("월령 값이 유효하지 않습니다: " + lunAge);
        }
    }

    private boolean isBetween(BigDecimal value, int min, int max) {
        return value.compareTo(BigDecimal.valueOf(min)) >= 0 && value.compareTo(BigDecimal.valueOf(max)) <= 0;
    }
}
