package com.mobyeoldol.starcast.calendar.application;

import com.mobyeoldol.starcast.calendar.domain.CelestialEvents;
import com.mobyeoldol.starcast.calendar.domain.repository.CalendarRepository;
import com.mobyeoldol.starcast.calendar.presentation.response.MonthlyAstronomicalResponse;
import com.mobyeoldol.starcast.calendar.presentation.request.MonthlyAstronomicalRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CalendarServiceImpl implements CalendarService {

    private final CalendarRepository calendarRepository;

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
}
