package com.mobyeoldol.starcast.notice.presentation;

import com.mobyeoldol.starcast.auth.application.service.AuthService;
import com.mobyeoldol.starcast.notice.application.NoticeService;
import com.mobyeoldol.starcast.notice.presentation.response.NoticeResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/notice")
public class NoticeController {
    private final NoticeService noticeService;
    private final AuthService authService;

    @PatchMapping("/setting")
    public ResponseEntity<NoticeResponse> changeNotice(
            @RequestParam(value = "notice") Boolean notice,
            @RequestHeader(value = "Authorization") String bearerToken)
    {
        log.info("[알림 수신여부 변경 API] PATCH /api/v1/notice/setting?notice={notice}");
        String profileUid = authService.authenticateMember(bearerToken);

        log.info("[알림 수신여부 변경 API] 알림 변경 Service 로직 수행");
        return ResponseEntity.ok().body(noticeService.changeNotice(profileUid, notice));
    }
}
