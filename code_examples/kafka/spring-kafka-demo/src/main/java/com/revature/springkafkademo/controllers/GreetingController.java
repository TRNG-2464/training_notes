package com.revature.springkafkademo.controllers;

import com.revature.springkafkademo.models.OrderRequest;
import com.revature.springkafkademo.services.GreetingProducer;
import com.revature.springkafkademo.services.OrderProducer;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/greet")
@AllArgsConstructor
public class GreetingController {

    private final GreetingProducer greetingProducer;

    @PostMapping
    public ResponseEntity<String> processGreeting(@RequestBody String eventData) {
        greetingProducer.sendGreeting(eventData);
        return ResponseEntity.ok("Greeting Event Sent with data: " + eventData);
    }
}
