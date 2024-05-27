package com.medaid.historytaking.test;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping ("/test")
public class TestController {
    private final TestService testService;

    @GetMapping("/")
    public String testingServer() {
        return testService.getTaxYear();
    }



}
