package com.revature.springkafkademo.models;

import lombok.*;

/*
 * This class represents an 'EventObject'. This structure
 * should not be used as an Entity. Instead, your service
 * should be responsible for converting DTOs from your controller
 * to Entities, and/or EventObjects which are appropriate for
 * the Repository/KafkaProducer respectively.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class OrderRequest {

    /*
     * orderId is a String here because Kafka expects a String
     * The orderId doesn't have to be a numeric value, it should
     * represent the event more wholistically (i.e. 'order_101'
     * instead of just '101' coming from, say, the database)
     */
    private String orderId;

    /*
     * Represents the status of an order:
     *  'ORDER_PLACED'
     *  'ORDER_CONFIRMED'
     *  'ORDER_SHIPPED'
     *  'ORDER_FULFILLED'
     *
     * It would be better design to use an enum
     * instead of a String for the rest of our application
     * and only write the enum to a string value for use
     * in the JSON serialization
     */
    private String status;

    /*
     * The eventPayload can include more event details
     * i.e. items ordered, etc...timestamps...
     */
    private String eventPayload;

}
