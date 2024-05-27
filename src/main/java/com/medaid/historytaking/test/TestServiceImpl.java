package com.medaid.historytaking.test;

import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {
    @Override
    public String getTaxYear() {
        return "Hello World";
    }
}
