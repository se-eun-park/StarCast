package com.mobyeoldol.starcast.calendar.application;

import com.mobyeoldol.starcast.calendar.presentation.response.MonthlyAstronomicalResponse;
import com.mobyeoldol.starcast.calendar.presentation.request.MonthlyAstronomicalRequest;

public interface CalendarService {
    public MonthlyAstronomicalResponse getMonthlyAstronomicalEvents(MonthlyAstronomicalRequest request);
}
