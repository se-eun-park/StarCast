package com.mobyeoldol.starcast.calendar.application;

import com.mobyeoldol.starcast.calendar.presentation.request.CalendarMainRequest;
import com.mobyeoldol.starcast.calendar.presentation.response.CalendarMainResponse;
import com.mobyeoldol.starcast.calendar.presentation.response.MonthlyAstronomicalResponse;
import com.mobyeoldol.starcast.calendar.presentation.request.MonthlyAstronomicalRequest;

public interface CalendarService {
    MonthlyAstronomicalResponse getMonthlyAstronomicalEvents(MonthlyAstronomicalRequest request);
    CalendarMainResponse getCalendarMain(String profileUid, CalendarMainRequest request);
}

