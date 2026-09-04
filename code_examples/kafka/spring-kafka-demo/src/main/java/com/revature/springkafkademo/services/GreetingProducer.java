package com.revature.springkafkademo.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class GreetingProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    // The @Qualifier here specifies which Producer template to use
    // since we need this qualifer, we cannot just use lombok's @AllArgsConstructor
    public GreetingProducer(@Qualifier("greetingKafkaTemplate")
                            KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    /*
     * send() handles serialization, connection management, and
     * network communication with the broker.
     */
    public void sendGreeting(String message) {
        kafkaTemplate.send("greetings", message);
    }
}
