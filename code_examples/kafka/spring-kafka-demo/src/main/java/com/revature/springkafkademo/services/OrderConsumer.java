package com.revature.springkafkademo.services;

import com.revature.springkafkademo.models.OrderRequest;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

@Service
public class OrderConsumer {

    @KafkaListener(
            topics = "orders",
            groupId = "orders-processing-group",
            containerFactory = "orderKafkaListenerContainerFactory" // reference to the container factory so we know how to perform deserialization
    )
    public void handleOrderEvent(
            ConsumerRecord<String, OrderRequest> record,
            Acknowledgment acknowledgment) {

        try {
            // Here Spring automatically handled the deserialization based on our consumer factory!
            OrderRequest event = record.value();
            System.out.printf("[Consumer] Processing order %s with status %s%n",
                    event.getOrderId(), event.getStatus());

            /*
             * Here, we only commit the offset AFTER processing completes
             * successfully. If an exception occurs above, this line is
             * never reached, and the message will be redelivered on the
             * next poll (message pull), instead of being silently skipped
             *
             * Think of this as the 'await' as far as Consumption goes...
             */
            acknowledgment.acknowledge();
            System.out.printf("[Consumer] Order %s acknowledged%n", event);


        } catch (Exception e) {
            System.err.println("Failed to process order: " + e.getMessage());
        }
    }
}
