package com.mobyeoldol.starcast.calendar.presentation.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MonthlyAstronomicalRequest {
    @NotBlank
    @Pattern(regexp = "\\d{4}-(0[1-9]|1[0-2])", message = "날짜 형식은 yyyy-MM이어야 합니다.")
    private String date;
}
