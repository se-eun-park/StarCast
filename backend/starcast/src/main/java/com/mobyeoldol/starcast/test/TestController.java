package com.mobyeoldol.starcast.test;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<String> test() {

        log.info("[server test api] GET /api/v1/test");
        return ResponseEntity.status(HttpStatus.OK).body("It works!!!");
    }
}
