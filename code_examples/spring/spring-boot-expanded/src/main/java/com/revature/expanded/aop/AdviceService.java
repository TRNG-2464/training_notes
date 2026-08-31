package com.revature.expanded.aop;

import org.springframework.stereotype.Service;

@Service
public class AdviceService {

    public String processData(double value) {
        if (value <= 0) {
            throw new RuntimeException("Amount must be positive");
        }
        return "The data provided: [" + value + "] was processed successfully";
    }
}


