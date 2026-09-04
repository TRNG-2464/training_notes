package com.revature.springkafkademo.services;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class GreetingConsumer {

    /*
     * Simple consumer on the greetings object - this consumer will just
     * print that the greeting message was received
     *
     * groupId : tells Kafka if these listeners should be part of the same
     * group on the same partition, or if each consumer method should have
     * it's own partition (different groupId)
     */
    @KafkaListener(topics = "greetings", groupId = "greeting-group-1")
    public void listen(String message) {
        System.out.println("Received Greeting: " + message);
    }

    /*
     * A second consumer on the same topic
     */
    @KafkaListener(topics = "greetings", groupId = "greeting-group-2")
    public void consumeGreeting(ConsumerRecord<String, String> record) {
        // Printing the key (string), partition (number), offset (number) and value (string)
        System.out.printf("Consumed event | key: %s | Partition: %d | Offset: %d | Value: %s%n",
                record.key(),
                record.partition(),
                record.offset(),
                record.value()
        );
    }
}
