package com.mobyeoldol.starcast.calendar.presentation.request;

import jakarta.validation.constraints.NotBlank;
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
    private LocalDate localDate;
    private GPS gps;

    static class GPS{
        @NotBlank(message = "주소1은 필수 입력 항목입니다.")
        private String address1;
        private String address2;
        private String address3;
        @NotBlank(message = "주소4는 필수 입력 항목입니다.")
        private String address4;
    }
}
