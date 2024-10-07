package com.mobyeoldol.starcast.calendar.application;

import com.mobyeoldol.starcast.calendar.domain.CelestialEvents;
import com.mobyeoldol.starcast.calendar.domain.repository.CalendarRepository;
import com.mobyeoldol.starcast.calendar.presentation.request.CalendarMainRequest;
import com.mobyeoldol.starcast.calendar.presentation.response.CalendarMainResponse;
import com.mobyeoldol.starcast.calendar.presentation.response.MonthlyAstronomicalResponse;
import com.mobyeoldol.starcast.calendar.presentation.request.MonthlyAstronomicalRequest;
import com.mobyeoldol.starcast.member.domain.Profile;
import com.mobyeoldol.starcast.member.domain.repository.ProfileRepository;
import com.mobyeoldol.starcast.place.domain.repository.FavouriteSpotRepository;
import com.mobyeoldol.starcast.place.domain.repository.MySpotRepository;
import com.mobyeoldol.starcast.place.domain.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CalendarServiceImpl implements CalendarService {

    private final CalendarRepository calendarRepository;
    private final ProfileRepository profileRepository;
    private final MySpotRepository mySpotRepository;
    private final PlaceRepository placeRepository;
    private final FavouriteSpotRepository favouriteSpotRepository;

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
//        log.info("[캘린더 메인 페이지 API] 1. Member의 actionPlaceType 조회");
//        Profile profile = getProfileInfo(profileUid);
//
//        CalendarMainResponse.CalendarMainResponseBuilder responseBuilder = CalendarMainResponse.builder();
//        log.info("[캘린더 메인 페이지 API] 2. MY_SPOT인지 GPS인지 보기");
//        if(profile.getActionPlaceType().equals(ActionPlaceType.MY_SPOT)){
//            log.info("[캘린더 메인 페이지 API] 2-1-1. MY_SPOT 이면, 내 장소 테이블에서 장소 아이디를 가져오기");
//
//            Optional<MySpot> optionalMySpot = mySpotRepository.findByProfile_ProfileUid(profileUid);
//            if (optionalMySpot.isPresent()) {
//                MySpot mySpot = optionalMySpot.get();
//                log.info("[캘린더 메인 페이지 API] 2-1-2. 장소 아이디로 장소 가져오고, 각 값 가져오기");
//                /*
//                위치별 출몰시각 테이블에서 월몰시 : 월몰분으로 묶어서 LocalTime 자료형인 어디에 담기
//                단중기예보 테이블에서 장소아이디로 하나를 찾고 습도, SKY, PTY 넣기
//
//                 */
//
//                Optional<Place> optionalPlace = placeRepository.findByPlaceUid(mySpot.getPlace().getPlaceUid());
//                if (optionalPlace.isPresent()) {
//                    responseBuilder.mySpot(makePlaceResponse(optionalPlace.get()));
//                }else{
//                    throw new IllegalArgumentException("내 장소의 정보를 조회할 수 없습니다.");
//                }
//            }
//        }else{
//            log.info("[캘린더 메인 페이지 API] 2-2. GPS 이면, request열고 유효성 검사 -> address 1, 4 필수 / address 2 or 3은 값이 하나 있어야함");
//            if(request.getGps()==null || request.getGps().getAddress1()==null || request.getGps().getAddress4()==null){
//                throw new IllegalArgumentException("주소 정보가 명확하지 않습니다.");
//            }
//
//            log.info("[캘린더 메인 페이지 API] 2-2-1. address1~4로 장소 아이디를 가져오기");
//            Optional<Place> optionalPlace = placeRepository.findByAddress(
//                    request.getGps().getAddress1(),
//                    request.getGps().getAddress2(),
//                    request.getGps().getAddress3(),
//                    request.getGps().getAddress4()
//            );
//
//            if (optionalPlace.isPresent()) {
//                Place place = optionalPlace.get();
//                log.info("[캘린더 메인 페이지 API] 2-2-2. 장소 아이디로 장소 가져오고, 각 값 가져오기");
//
//                // GPS 정보로 응답 세팅
//                responseBuilder.myGPS(makePlaceResponse(place));
//            } else {
//                throw new IllegalArgumentException("GPS 유효성 검사 실패");
//            }
//
//            log.info("[캘린더 메인 페이지 API] 2-2-2. 장소 아이디로 장소 가져오고, 각 값 가져오기");
//        }
//
//        log.info("[캘린더 메인 페이지 API] 3. 즐겨찾기 리스트");
//
//        List<FavouriteSpot> favouriteSpots = favouriteSpotRepository.findByProfileUid(profileUid);
//        List<CalendarMainResponse.FavouritePlace> favouritePlaces = new ArrayList<>();
//
//        log.info("[캘린더 메인 페이지 API] 3-1. 프로필 아이디로 모든 즐겨찾기 가져오기");
//        for (FavouriteSpot favouriteSpot : favouriteSpots) {
//            log.info("[캘린더 메인 페이지 API] 3-2. 즐겨찾기의 장소아이디로 각 장소가져오고 각 값 가져오기");
//            Optional<Place> optionalPlace = placeRepository.findByPlaceUid(favouriteSpot.getPlace().getPlaceUid());
//
//            CalendarMainResponse.FavouritePlace favouritePlace = CalendarMainResponse.FavouritePlace.builder()
//                    .placeUid(place.getPlaceUid())
//                    .address(place.getAddress1()+" "+place.getAddress2()+" "+place.getAddress3()+" "+place.getAddress4())
//                    .details(place.getAddress4())
//                    .placeType(place.getType())
//                    .weatherOfTheNight()
//                    .best()
//                    .moonSetTime()
//                    .isPlanned()
//                    .build();
//
//            favouritePlaces.add(makePlaceResponse(place));
//        }
//        responseBuilder.favouritePlaceList(favouritePlaces);
//
//        return responseBuilder.build();
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
