package com.mobyeoldol.starcast.calendar.presentation;

import com.mobyeoldol.starcast.auth.application.service.AuthService;
import com.mobyeoldol.starcast.calendar.application.CalendarService;
import com.mobyeoldol.starcast.calendar.presentation.request.CalendarMainRequest;
import com.mobyeoldol.starcast.calendar.presentation.request.MonthlyAstronomicalRequest;
import com.mobyeoldol.starcast.calendar.presentation.response.CalendarMainResponse;
import com.mobyeoldol.starcast.calendar.presentation.response.MonthlyAstronomicalResponse;
import com.mobyeoldol.starcast.global.template.BaseResponseTemplate;
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
    public ResponseEntity<BaseResponseTemplate<?>> getMonthlyAstronomicalEvents(
            @Valid @RequestBody MonthlyAstronomicalRequest request,
            BindingResult bindingResult,
            @RequestHeader(value = "Authorization") String bearerToken) {

        log.info("[월별 천문현상 조회 API] GET /api/v1/calendar/astronomical-events");

        log.info("[월별 천문현상 조회 API] 요청 유효성 검사 실패 시 에러 응답 반환");
        if (bindingResult.hasErrors()) {
            BaseResponseTemplate<?> errorResponse = BaseResponseTemplate.failure(400, "body에 date는 필수입니다. [양식 : yyyy-MM]");
            return ResponseEntity.status(400).body(errorResponse);
        }

        String profileUid = authService.authenticateMember(bearerToken);

        log.info("[월별 천문현상 조회 API] Service 로직 수행");
        MonthlyAstronomicalResponse response = calendarService.getMonthlyAstronomicalEvents(request);

        log.info("[월별 천문현상 조회 API] 응답 반환");
        BaseResponseTemplate<MonthlyAstronomicalResponse> result = BaseResponseTemplate.success(response);
        return ResponseEntity.ok(result);
    }

    @GetMapping()
    public ResponseEntity<BaseResponseTemplate<?>> getCalendarMain(
            @Valid @RequestBody CalendarMainRequest request,
            BindingResult bindingResult,
            @RequestHeader(value = "Authorization") String bearerToken)
    {
        log.info("[캘린더 메인 페이지 API] GET /api/v1/calendar");

        log.info("[캘린더 메인 페이지 API] 요청 유효성 검사 실패 시 에러 응답 반환");
        if (bindingResult.hasErrors()) {
            BaseResponseTemplate<?> errorResponse = BaseResponseTemplate.failure(400, "body에 date와 address1, address4는 필수입니다.");
            return ResponseEntity.status(400).body(errorResponse);
        }

        String profileUid = "profile-uid-01";// authService.authenticateMember(bearerToken);

        log.info("[캘린더 메인 페이지 API] 서비스 로직 수행");
        CalendarMainResponse response = calendarService.getCalendarMain(profileUid, request);

        BaseResponseTemplate<CalendarMainResponse> result = BaseResponseTemplate.success(response);
        return ResponseEntity.ok(result);
    }

}

