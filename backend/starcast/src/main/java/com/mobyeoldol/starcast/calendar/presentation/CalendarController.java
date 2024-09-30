package com.mobyeoldol.starcast.calendar.presentation;

import com.mobyeoldol.starcast.auth.application.service.AuthService;
import com.mobyeoldol.starcast.calendar.application.CalendarService;
import com.mobyeoldol.starcast.calendar.presentation.request.MonthlyAstronomicalRequest;
import com.mobyeoldol.starcast.calendar.presentation.response.MonthlyAstronomicalResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/calendar")
public class CalendarController {

    private final CalendarService calendarService;
    private final AuthService authService;

    @GetMapping("/astronomical-events")
    public ResponseEntity<MonthlyAstronomicalResponse> getMonthlyAstronomicalEvents(
            @Valid @RequestBody MonthlyAstronomicalRequest request,
            BindingResult bindingResult,
            @RequestHeader(value = "Authorization") String bearerToken)
    {
        log.info("[월별 천문현상 조회 API] GET /api/v1/calendar/astronomical-events");

        if (bindingResult.hasErrors()) {
            log.error("[월별 천문현상 조회 API] 유효성 검사 실패: {}", bindingResult.getFieldError().getDefaultMessage());
            throw new IllegalArgumentException("[월별 천문현상 조회 API] 날짜는 필수입니다. [양식 : yyyy-MM]");
        }

        String profileUid = authService.authenticateMember(bearerToken);

        log.info("[월별 천문현상 조회 API] Service 로직 수행");
        return ResponseEntity.ok().body(calendarService.getMonthlyAstronomicalEvents(request));
    }
}
