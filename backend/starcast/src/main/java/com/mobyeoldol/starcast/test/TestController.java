package com.mobyeoldol.starcast.test;

import com.mobyeoldol.starcast.global.template.BaseResponseTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/test")
public class TestController {

    @GetMapping()
    public ResponseEntity<BaseResponseTemplate<String>> test() {

        log.info("[server test api] GET /api/v1/test");

        BaseResponseTemplate<String> successResponse = BaseResponseTemplate.success("Server is running!");
        return ResponseEntity.ok(successResponse);
    }
}

