package com.mobyeoldol.starcast.calendar.application;

import com.mobyeoldol.starcast.calendar.domain.CelestialEvents;
import com.mobyeoldol.starcast.calendar.domain.Forecast;
import com.mobyeoldol.starcast.calendar.domain.LunarCycle;
import com.mobyeoldol.starcast.calendar.domain.MoonriseMoonsetTimes;
import com.mobyeoldol.starcast.calendar.domain.enums.LightPolluitonStatus;
import com.mobyeoldol.starcast.calendar.domain.enums.MoonStatus;
import com.mobyeoldol.starcast.calendar.domain.enums.PrecipitationStatus;
import com.mobyeoldol.starcast.calendar.domain.enums.WeatherStatus;
import com.mobyeoldol.starcast.calendar.domain.repository.CalendarRepository;
import com.mobyeoldol.starcast.calendar.domain.repository.ForecastRepository;
import com.mobyeoldol.starcast.calendar.domain.repository.LunarCycleRepository;
import com.mobyeoldol.starcast.calendar.domain.repository.MoonriseMoonsetTimesRepository;
import com.mobyeoldol.starcast.calendar.presentation.request.CalendarMainRequest;
import com.mobyeoldol.starcast.calendar.presentation.response.CalendarMainResponse;
import com.mobyeoldol.starcast.calendar.presentation.response.MonthlyAstronomicalResponse;
import com.mobyeoldol.starcast.calendar.presentation.request.MonthlyAstronomicalRequest;
import com.mobyeoldol.starcast.member.domain.Profile;
import com.mobyeoldol.starcast.member.domain.repository.ProfileRepository;
import com.mobyeoldol.starcast.place.domain.FavouriteSpot;
import com.mobyeoldol.starcast.place.domain.MySpot;
import com.mobyeoldol.starcast.place.domain.Place;
import com.mobyeoldol.starcast.place.domain.Plan;
import com.mobyeoldol.starcast.place.domain.enums.MainPlace;
import com.mobyeoldol.starcast.place.domain.enums.PlaceType;
import com.mobyeoldol.starcast.place.domain.repository.FavouriteSpotRepository;
import com.mobyeoldol.starcast.place.domain.repository.MySpotRepository;
import com.mobyeoldol.starcast.place.domain.repository.PlaceRepository;
import com.mobyeoldol.starcast.place.domain.repository.PlanRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
@RequiredArgsConstructor
public class CalendarServiceImpl implements CalendarService {

    private final CalendarRepository calendarRepository;
    private final ProfileRepository profileRepository;
    private final MySpotRepository mySpotRepository;
    private final PlaceRepository placeRepository;
    private final FavouriteSpotRepository favouriteSpotRepository;
    private final MoonriseMoonsetTimesRepository moonriseMoonsetTimesRepository;
    private final PlanRepository planRepository;
    private final ForecastRepository forecastRepository;
    private final LunarCycleRepository lunarCycleRepository;


    @Transactional(readOnly = true)
    @Override
    public MonthlyAstronomicalResponse getMonthlyAstronomicalEvents(MonthlyAstronomicalRequest request) {
        log.info("[월별 천문현상 조회 API] 1. 일자를 1일로 임시 설정");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");
        YearMonth yearMonth = YearMonth.parse(request.getDate(), formatter);

        int year = yearMonth.getYear();
        int month = yearMonth.getMonthValue();

        log.info("[월별 천문현상 조회 API] 2. 'year'와 'month'를 '년_월' 형식의 문자열로 변환하여 DB 조회");
        String yearMonthString = String.format("%04d_%02d", year, month);

        log.info("[월별 천문현상 조회 API] 3. 해당 월의 천문현상 리스트 가져오기");
        List<CelestialEvents> celestialEvents = calendarRepository.findByCelestialEventUidStartingWith(yearMonthString);

        log.info("[월별 천문현상 조회 API] 4. 데이터를 MonthlyAstronomicalResponse 형식으로 변환");
        Map<LocalDate, List<MonthlyAstronomicalResponse.EventsOfTheDay>> eventsGroupedByDate = celestialEvents.stream()
                .collect(Collectors.groupingBy(event -> LocalDate.of(
                                Integer.parseInt(event.getCelestialEventUid().substring(0, 4)),
                                Integer.parseInt(event.getCelestialEventUid().substring(5, 7)),
                                Integer.parseInt(event.getCelestialEventUid().substring(8, 10))),
                                TreeMap::new,
                        Collectors.mapping(event -> MonthlyAstronomicalResponse.EventsOfTheDay.builder()
                                .seq(Integer.parseInt(event.getCelestialEventUid().substring(11)))
                                .title(event.getTitle())
                                .event(event.getEvent() != null && !event.getEvent().isEmpty() ? event.getEvent() : null)  // 이벤트가 존재할 때만
                                .time(event.getCelestialEventHour() > 0 && event.getCelestialEventMin() >=0 ?
                                        LocalTime.of(event.getCelestialEventHour(), event.getCelestialEventMin()) : null)  // 시와 분이 존재할 때만
                                .build(), Collectors.toList())
                ));

        List<MonthlyAstronomicalResponse.EventsOfTheMonth> eventsOfTheMonth = eventsGroupedByDate.entrySet().stream()
                .map(entry -> MonthlyAstronomicalResponse.EventsOfTheMonth.builder()
                        .date(entry.getKey())
                        .eventsOfTheDay(entry.getValue())
                        .build())
                .collect(Collectors.toList());

        log.info("[월별 천문현상 조회 API] 5. 응답 반환");
        return MonthlyAstronomicalResponse.builder()
                .year(year)
                .month(month)
                .eventsOfTheMonth(eventsOfTheMonth)
                .build();
    }

    @Override
    public CalendarMainResponse getCalendarMain(String profileUid, CalendarMainRequest request) {
        log.info("[캘린더 메인 페이지 API] 1. Member의 actionPlaceType 조회");
        Profile profile = getProfileInfo(profileUid);

        CalendarMainResponse.CalendarMainResponseBuilder<?, ?> responseBuilder = CalendarMainResponse.builder();

        log.info("[캘린더 메인 페이지 API] 2. MY_SPOT or GPS인지 보기 ["+profile.getActionPlaceType()+"]");
        if(profile.getActionPlaceType().equals(MainPlace.GPS)){
            log.info("[캘린더 메인 페이지 API] 2-2-1. GPS 이면, request열고 유효성 검사 -> address 1, 4 필수 / address 2 or 3은 값이 하나 있어야함");
            if(request.getGps()==null || request.getGps().getAddress1()==null || request.getGps().getAddress4()==null){
                throw new IllegalArgumentException("[캘린더 메인 페이지 API] 2-2-1. 주소 정보가 명확하지 않습니다.");
            }

            log.info("[캘린더 메인 페이지 API] 2-2-1. address1~4로 장소 아이디를 가져오기");
            Optional<Place> optionalPlace = placeRepository.findByAddress1AndAddress2AndAddress3AndAddress4AndType(
                    request.getGps().getAddress1(),
                    request.getGps().getAddress2(),
                    request.getGps().getAddress3(),
                    request.getGps().getAddress4(),
                    PlaceType.EUP_MYUN_DONG
            );

            if (optionalPlace.isEmpty()) {
                log.warn("[캘린더 메인 페이지 API] 2-2-2. 장소 정보가 없습니다.");
                throw new IllegalArgumentException("[캘린더 메인 페이지 API] 2-2-2. 장소 정보가 없습니다.");
            }
            Place place = optionalPlace.get();

            String placeUid = place.getPlaceUid();
            CalendarMainResponse.WeatherOfTheNight weatherOfTheNight = getWeatherOfTheNight(placeUid, request.getDate());

            CalendarMainResponse.MyGPS curMyGPS = CalendarMainResponse.MyGPS.builder()
                    .placeUid(placeUid)
                    .address(makeAddress(place.getAddress1(), place.getAddress2(), place.getAddress3(), place.getAddress4()))
                    .details(place.getAddress4())
                    .placeType(place.getType())
                    .weatherOfTheNight(weatherOfTheNight)
                    .best(createBestDay(placeUid, request.getDate(), weatherOfTheNight))
                    .moonSetTime(getMoonSetTime(request.getDate(), place))
                    .isPlanned(checkIfPlanned(placeUid, request.getDate())) // placeUid 장소아이디에 대해 연결된 plan 테이블에 값이 있는지 확인하고 21시~00시 사에이 있다면 알려준다.
                    .build();

            responseBuilder.myGPS(curMyGPS);
        }else if(profile.getActionPlaceType().equals(MainPlace.MY_SPOT)){
            log.info("[캘린더 메인 페이지 API] 2-1-1. MY_SPOT 이면, 내 장소 테이블에서 장소 아이디를 가져오기");

            Optional<MySpot> optionalMySpot = mySpotRepository.findByProfile_ProfileUid(profileUid);
            if (optionalMySpot.isPresent()) {
                MySpot mySpot = optionalMySpot.get();
                log.info("[캘린더 메인 페이지 API] 2-1-1. OK");
                log.info("[캘린더 메인 페이지 API] 2-1-2. 장소 아이디로 장소 가져오고, 각 값 가져오기");

                String placeUid = mySpot.getPlace().getPlaceUid();

                Optional<Place> optionalPlace = placeRepository.findByPlaceUid(placeUid);
                if (optionalPlace.isPresent()) {
                    log.info("[캘린더 메인 페이지 API] OK");
                    Place place = optionalPlace.get();

                    CalendarMainResponse.WeatherOfTheNight weatherOfTheNight = getWeatherOfTheNight(placeUid, request.getDate());

                    CalendarMainResponse.MySpot curMySpot = CalendarMainResponse.MySpot.builder()
                            .placeUid(placeUid)
                            .address(makeAddress(place.getAddress1(), place.getAddress2(), place.getAddress3(), place.getAddress4()))
                            .details(place.getAddress4())
                            .placeType(place.getType())
                            .weatherOfTheNight(weatherOfTheNight)
                            .best(createBestDay(placeUid, request.getDate(), weatherOfTheNight))
                            .moonSetTime(getMoonSetTime(request.getDate(), place))
                            .isPlanned(checkIfPlanned(placeUid, request.getDate())) // placeUid 장소아이디에 대해 연결된 plan 테이블에 값이 있는지 확인하고 21시~00시 사에이 있다면 알려준다.
                            .build();

                    responseBuilder.mySpot(curMySpot);
                }else{
                    throw new IllegalArgumentException("내 장소의 정보를 조회할 수 없습니다.");
                }
            }
            log.info("[캘린더 메인 페이지 API] 2번 끗");
        }
        log.info("[캘린더 메인 페이지 API] 3. 즐겨찾기 리스트");
        List<FavouriteSpot> favouriteSpotList = favouriteSpotRepository.findByProfile_ProfileUidAndIsDeletedFalse(profileUid);
        if(favouriteSpotList.isEmpty())
            return responseBuilder.build();

        List<CalendarMainResponse.FavouritePlace> favouritePlaceList = new ArrayList<>();
        for(FavouriteSpot favouriteSpot : favouriteSpotList){
            Optional<Place> optionalPlace = placeRepository.findByPlaceUid(favouriteSpot.getPlace().getPlaceUid());
            if (optionalPlace.isEmpty()) {
                log.warn("[캘린더 메인 페이지 API] 장소 정보가 없습니다.");
                return null;
            }
            Place place = optionalPlace.get();

            String placeUid = place.getPlaceUid();
            CalendarMainResponse.WeatherOfTheNight weatherOfTheNight = getWeatherOfTheNight(placeUid, request.getDate());

            CalendarMainResponse.FavouritePlace favouritePlace = CalendarMainResponse.FavouritePlace.builder()
                    .placeUid(placeUid)
                    .address(makeAddress(place.getAddress1(), place.getAddress2(), place.getAddress3(), place.getAddress4()))
                    .details(place.getAddress4())
                    .placeType(place.getType())
                    .weatherOfTheNight(weatherOfTheNight)
                    .best(createBestDay(placeUid, request.getDate(), weatherOfTheNight))
                    .moonSetTime(getMoonSetTime(request.getDate(), place))
                    .isPlanned(checkIfPlanned(placeUid, request.getDate())) // placeUid 장소아이디에 대해 연결된 plan 테이블에 값이 있는지 확인하고 21시~00시 사에이 있다면 알려준다.
                    .build();

            favouritePlaceList.add(favouritePlace);
        }
        responseBuilder.favouritePlaceList(favouritePlaceList);
        return responseBuilder.build();
    }

    private String makeAddress(String address1, String address2, String address3, String address4) {
        return Stream.of(address1, address2, address3, address4)
                .filter(Objects::nonNull)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.joining(" "));
    }

    private CalendarMainResponse.WeatherOfTheNight getWeatherOfTheNight(String placeUid, LocalDate date) {
        log.info("[입력값 확인] placeUid : {}, date : {}", placeUid, date);

        log.info("[캘린더 메인 페이지 API] 1. Forecast 데이터를 가져옴");
        String forecastUid = date.format(DateTimeFormatter.ofPattern("yyyy_MM_dd"))+"_"+placeUid;
        log.info("1. forecastUid : {} [getCloudCoverage]", forecastUid);

        Forecast forecast = forecastRepository.findById(forecastUid)
                .orElseThrow(() -> new EntityNotFoundException("해당 날짜의 단중기예보를 찾을 수 없습니다."));

        log.info("[캘린더 메인 페이지 API] 2. Place에서 광공해 값을 가져옴");
        Optional<Place> optionalPlace = placeRepository.findByPlaceUid(placeUid);
        if (optionalPlace.isEmpty()) {
            log.warn("[캘린더 메인 페이지 API] 장소 정보가 없습니다. placeUid: {}", placeUid);
            return null;
        }
        Place place = optionalPlace.get();
        LightPolluitonStatus lps = getLightPollution(place.getLightPollution());

        Optional<LunarCycle> optionalLunarCycle = lunarCycleRepository.findByLunarCycleUid(date.format(DateTimeFormatter.ofPattern("yyyy_MM_dd")));
        if (optionalLunarCycle.isEmpty()) {
            log.warn("[캘린더 메인 페이지 API] 월령 정보가 없습니다. date: {}", date);
            return null;
        }
        LunarCycle lunarCycle = optionalLunarCycle.get();
        double moonPhaseFactor = getMoonPhaseFactor(lunarCycle.getLunarAge().doubleValue());

        log.info("21 : {}, 22 : {}, 23 : {}, 00 : {}, 01 : {}, 02 : {}"
                ,forecast.getSky21(), forecast.getSky22(), forecast.getSky23(), forecast.getSky00(), forecast.getSky01(), forecast.getSky02());

        return CalendarMainResponse.WeatherOfTheNight.builder()
                .hour21(CalendarMainResponse.Hour.builder()
                        .castarPoint(calculateCastarPoint(forecast.getSky21(), moonPhaseFactor, lps, forecast.getHumidity21(), forecast.getPty21()))
                        .cloudCoverage(getWeatherStatus(forecast.getSky21()))
                        .precipitation(getPrecipitationStatus(forecast.getPty21()))
                        .humidity(forecast.getHumidity21())
                        .build())
                .hour22(CalendarMainResponse.Hour.builder()
                        .castarPoint(calculateCastarPoint(forecast.getSky22(), moonPhaseFactor, lps, forecast.getHumidity22(), forecast.getPty22()))
                        .cloudCoverage(getWeatherStatus(forecast.getSky22()))
                        .precipitation(getPrecipitationStatus(forecast.getPty22()))
                        .humidity(forecast.getHumidity22())
                        .build())
                .hour23(CalendarMainResponse.Hour.builder()
                        .castarPoint(calculateCastarPoint(forecast.getSky23(), moonPhaseFactor, lps, forecast.getHumidity23(), forecast.getPty23()))
                        .cloudCoverage(getWeatherStatus(forecast.getSky23()))
                        .precipitation(getPrecipitationStatus(forecast.getPty23()))
                        .humidity(forecast.getHumidity23())
                        .build())
                .hour00(CalendarMainResponse.Hour.builder()
                        .castarPoint(calculateCastarPoint(forecast.getSky00(), moonPhaseFactor, lps, forecast.getHumidity00(), forecast.getPty00()))
                        .cloudCoverage(getWeatherStatus(forecast.getSky00()))
                        .precipitation(getPrecipitationStatus(forecast.getPty00()))
                        .humidity(forecast.getHumidity00())
                        .build())
                .hour01(CalendarMainResponse.Hour.builder()
                        .castarPoint(calculateCastarPoint(forecast.getSky01(), moonPhaseFactor, lps, forecast.getHumidity01(), forecast.getPty01()))
                        .cloudCoverage(getWeatherStatus(forecast.getSky01()))
                        .precipitation(getPrecipitationStatus(forecast.getPty01()))
                        .humidity(forecast.getHumidity01())
                        .build())
                .hour02(CalendarMainResponse.Hour.builder()
                        .castarPoint(calculateCastarPoint(forecast.getSky02(), moonPhaseFactor, lps, forecast.getHumidity02(), forecast.getPty02()))
                        .cloudCoverage(getWeatherStatus(forecast.getSky02()))
                        .precipitation(getPrecipitationStatus(forecast.getPty02()))
                        .humidity(forecast.getHumidity02())
                        .build())
                .build();
    }

    private double calculateCastarPoint(Integer skyValue, double moonPhaseFactor, LightPolluitonStatus lps, BigDecimal humidity, Integer precipitation) {
        double cloudFactor = getCloudFactor(skyValue);
        double humidityValue = getHumidityFactor(humidity);
        double precipitationValue = getPrecipitationFactor(precipitation);

        log.info("구름: {}, 습도: {}, 강수확률: {}",
                skyValue, humidity, precipitation);

        log.info("구름(cloudFactor): {}, 월령(moonPhaseFactor): {}, 광공해(lightPollution): {}, 습도(humidityValue): {}, 강수확률(precipitationValue): {}",
                cloudFactor, moonPhaseFactor, lps, humidityValue, precipitationValue);

        double lightPollutionFactor = switch (lps) {
            case LOW -> 1.0;
            case MEDIUM -> 0.75;
            case HIGH -> 0.5;
        };

        // castarPoint 계산: (구름 * 월령 * 광공해) - 습도 - 강수확률
        DecimalFormat df = new DecimalFormat("###.##");
        double castarPoint = Math.round((100 * (cloudFactor * moonPhaseFactor * lightPollutionFactor) - humidityValue - precipitationValue) * 10.0) / 10.0;
        return castarPoint<=0?0.0d:castarPoint;
    }

    private double getCloudFactor(int skyCode) {
        switch (skyCode) {
            case 1:
                return 1.0;
            case 3:
                return 0.01;
            default:
                return 0.25;
        }
    }

    private double getMoonPhaseFactor(double lunAge) {
        if (lunAge >= 0 && lunAge <= 6 || lunAge >= 24 && lunAge <= 32) {
            return 1.0;
        } else if (lunAge >= 6 && lunAge <= 11 || lunAge >= 19 && lunAge <= 24) {
            return 0.75;
        } else {
            return 0.5;
        }
    }

    private MoonStatus getMoonPhaseStatus(double lunAge) {
        if (lunAge >= 0 && lunAge <= 6 || lunAge >= 24 && lunAge <= 32) {
            return MoonStatus.DARK_MOON;
        } else if (lunAge >= 6 && lunAge <= 11 || lunAge >= 19 && lunAge <= 24) {
            return MoonStatus.MOON;
        } else {
            return MoonStatus.BRIGHT_MOON;
        }
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

    private double getHumidityFactor(BigDecimal humidity) {
        double humidityValue = humidity.doubleValue();
        if (humidityValue <= 30) {
            return 0.0;
        } else if (humidityValue <= 60) {
            return 0.2;
        } else {
            return 0.4;
        }
    }

    private double getPrecipitationFactor(int precipitationCode) {
        switch (precipitationCode) {
            case 0:
                return 0.0;
            case 1, 3:
                return 0.2;
            case 2:
                return 0.4;
            case 4:
                return 0.5;
            default:
                return 0.0;
        }
    }

    private WeatherStatus getWeatherStatus(int code) {
        return Arrays.stream(WeatherStatus.values())
                .filter(status -> status.getCode() == code)
                .findFirst()
                .orElse(WeatherStatus.CLEAR);
    }

    private PrecipitationStatus getPrecipitationStatus(int code) {
        return Arrays.stream(PrecipitationStatus.values())
                .filter(status -> status.getCode() == code)
                .findFirst()
                .orElse(PrecipitationStatus.NONE);
    }

    private CalendarMainResponse.BestDay createBestDay(String placeUid, LocalDate date, CalendarMainResponse.WeatherOfTheNight weatherOfTheNight) {
        Map<String, Double> castarPointsMap = new HashMap<>();
        castarPointsMap.put("21", weatherOfTheNight.getHour21().getCastarPoint());
        castarPointsMap.put("22", weatherOfTheNight.getHour22().getCastarPoint());
        castarPointsMap.put("23", weatherOfTheNight.getHour23().getCastarPoint());
        castarPointsMap.put("00", weatherOfTheNight.getHour00().getCastarPoint());
        castarPointsMap.put("01", weatherOfTheNight.getHour01().getCastarPoint());
        castarPointsMap.put("02", weatherOfTheNight.getHour02().getCastarPoint());

        String bestHour = castarPointsMap.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .get()
                .getKey();

        Optional<Place> optionalPlace = placeRepository.findByPlaceUid(placeUid);
        if (optionalPlace.isEmpty()) {
            log.warn("[캘린더 메인 페이지 API] 장소 정보가 없습니다. placeUid: {}", placeUid);
            return null;
        }
        Place place = optionalPlace.get();
        LightPolluitonStatus lps = getLightPollution(place.getLightPollution());

        Optional<LunarCycle> optionalLunarCycle = lunarCycleRepository.findByLunarCycleUid(date.format(DateTimeFormatter.ofPattern("yyyy_MM_dd")));
        if (optionalLunarCycle.isEmpty()) {
            log.warn("[캘린더 메인 페이지 API] 월령 정보가 없습니다. date: {}", date);
            return null;
        }
        LunarCycle lunarCycle = optionalLunarCycle.get();
        double moonPhaseFactor = getMoonPhaseFactor(lunarCycle.getLunarAge().doubleValue());

        return CalendarMainResponse.BestDay.builder()
                .hour(bestHour)
                .moonPhase(getMoonPhaseStatus(moonPhaseFactor))
                .lightPollution(lps)
                .build();
    }

    private String getMoonSetTime(LocalDate date, Place place) {
        String moonRiseSetTimeUid = date.format(DateTimeFormatter.ofPattern("yyyy_MM_dd"))+"_"+place.getPlaceUid();
        log.info("1. moonRiseSetTimeUid : {} [getMoonSetTime]", moonRiseSetTimeUid);

        log.info("2. moon_rise_set_time_uid로 위치별 출몰시각 값 조회");
        MoonriseMoonsetTimes moonriseMoonsetTimes = moonriseMoonsetTimesRepository.findByMoonRiseSetTimeUid(moonRiseSetTimeUid)
                .orElseThrow(() -> new EntityNotFoundException("해당 날짜의 위치별 출몰시각을 찾을 수 없습니다."));

        return String.format("%02d:%02d", moonriseMoonsetTimes.getMoonsetHour(), moonriseMoonsetTimes.getMoonsetMin());

    }

    private CalendarMainResponse.IsPlanned checkIfPlanned(String placeUid, LocalDate date) {
        log.info("checkIfPlanned 테스트 : {}, date : {}",placeUid,date);
        Optional<Plan> optionalPlan = planRepository.findByPlace_PlaceUidAndDateTimeBetweenAndIsDeletedFalse(
                placeUid, LocalDateTime.of(date, LocalTime.of(21, 0)), LocalDateTime.of(date.plusDays(1), LocalTime.of(3, 0))
        );
        if (optionalPlan.isPresent()) {
            Plan plan = optionalPlan.get();
            return CalendarMainResponse.IsPlanned.builder()
                    .planUid(plan.getPlanUid())
                    .hour(String.format("%02d", plan.getDateTime().getHour()))
                    .build();
        }
        return null;
    }

    private Profile getProfileInfo(String profileUid){
        Optional<Profile> optionalProfile = profileRepository.findById(profileUid);
        if(optionalProfile.isEmpty()) {
            log.error("[getProfileInfo 메서드] 1-2. 해당 프로필 정보를 찾을 수 없습니다.");
            throw new IllegalArgumentException("해당 프로필 정보를 찾을 수 없습니다.");
        }
        return optionalProfile.get();
    }
}


