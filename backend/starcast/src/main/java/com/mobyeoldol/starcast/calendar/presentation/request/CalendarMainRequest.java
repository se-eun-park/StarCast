package com.mobyeoldol.starcast.calendar.presentation.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CalendarMainRequest {
    @NotNull(message = "날짜 형식은 yyyy-MM-dd 이어야 합니다.")
    private LocalDate date;
    private GPS gps;

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GPS{
        private String address1;
        private String address2;
        private String address3;
        private String address4;
    }
}
