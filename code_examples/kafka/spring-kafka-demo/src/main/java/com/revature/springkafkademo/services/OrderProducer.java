package com.revature.springkafkademo.services;

import com.revature.springkafkademo.models.OrderRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class OrderProducer {

    private final KafkaTemplate<String, OrderRequest> kafkaTemplate;

    // @Qualifier to specify the template for use - see OrderKafkaConfig
    public OrderProducer(@Qualifier("orderKafkaTemplate")
                         KafkaTemplate<String, OrderRequest> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendOrderEvent(OrderRequest event) {
        /*
         * Sending with a KEY (orderId) is important. Kafka uses the key to
         * decide which partition the event goes to. All events with the
         * same key will always land on the SAME partition, which preserves
         * ordering for that specific order.
         *
         * Note: This 'orderId' is used by Kafka to allow us to identify and
         * process related events. Kafka also gives each event its own unique
         * eventId.
         *
         * An order, for instance, can have a 'place_order' event and a
         * 'process_payment' event, sent at different times due to a delay
         * in the payment processing system.
         */
        System.out.println("[Producer] Attempting to send Order: " + event);
        CompletableFuture<SendResult<String, OrderRequest>> future =
                kafkaTemplate.send("orders", event.getOrderId(), event);

        future.whenComplete(
                // whenComplete is informed that the production was complete
                // After the acknowledgement from Kafka
                (result, ex) -> {
            if (ex != null) {
                // This means that an Error was thrown...
                System.err.println("[Producer] Failed to send order: " + ex.getMessage());
            } else {
                // These println statements represent log messages that we send
                // after the CompletableFuture returned from 'send'
                System.out.println("[Producer] Order successfully sent to partition " +
                        result.getRecordMetadata().partition());
                System.out.println("[Producer] Order details: " +
                        result.getProducerRecord().value().toString()); // prints the OrderRequest object
                                                                        // Note: I could also print the
                                                                        // OrderRequest using event.toString()
                                                                        // but this shows we can access that
                                                                        // data using 'value()' on the ProducerRecord
            }
        });
    }
}
