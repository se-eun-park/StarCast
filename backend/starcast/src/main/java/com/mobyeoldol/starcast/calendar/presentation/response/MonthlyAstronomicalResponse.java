package com.mobyeoldol.starcast.calendar.presentation.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MonthlyAstronomicalResponse {
    private int year;
    private int month;
    private List<EventsOfTheMonth> eventsOfTheMonth;

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class EventsOfTheMonth {
        private LocalDate date;
        private List<EventsOfTheDay> eventsOfTheDay;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class EventsOfTheDay {
        private int seq;
        private String title;
        private String event;
        @JsonFormat(pattern = "HH:mm")
        private LocalTime time;
    }
}
