package com.mobyeoldol.starcast.business.presentation;

import com.mobyeoldol.starcast.business.application.BusinessService;
import com.mobyeoldol.starcast.business.presentation.request.MainRequest;
import com.mobyeoldol.starcast.business.presentation.response.MainResponse;
import com.mobyeoldol.starcast.global.template.BaseResponseTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/main")
public class BusinessController {

    private final BusinessService businessService;

    @GetMapping()
    public ResponseEntity<BaseResponseTemplate<?>> getMain(
            @RequestBody MainRequest request,
            BindingResult bindingResult
    ){
        log.info("[메인(내 장소, GPS)에 대해 캐스타 점수 불러오기] GET /api/v1/main");
        if (bindingResult.hasErrors()) {
            log.error("[메인(내 장소, GPS)에 대해 캐스타 점수 불러오기] 유효성 검사 실패: {}", bindingResult.getFieldError().getDefaultMessage());
            BaseResponseTemplate<?> errorResponse = BaseResponseTemplate.failure(400, "[메인(내 장소, GPS)에 대해 캐스타 점수 불러오기] 주소1과 주소4는 필수항목입니다.");
            return ResponseEntity.status(400).body(errorResponse);
        }

        String profileUid = "profile-uid-01";//authService.authenticateMember(bearerToken);

        MainResponse response = businessService.getMain(profileUid, request);

        BaseResponseTemplate<?> successResponse = BaseResponseTemplate.success(response);
        return ResponseEntity.ok().body(successResponse);
    }
}
