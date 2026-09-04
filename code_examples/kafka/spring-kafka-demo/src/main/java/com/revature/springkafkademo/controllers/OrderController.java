package com.revature.springkafkademo.controllers;

import com.revature.springkafkademo.models.OrderRequest;
import com.revature.springkafkademo.services.OrderProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderProducer orderProducer;

    public OrderController(OrderProducer orderProducer) {
        this.orderProducer = orderProducer;
    }

    @PostMapping("/{orderId}")
    public ResponseEntity<String> placeOrder(
            @PathVariable String orderId,
            @RequestBody OrderRequest request) {

        // set the orderId on the object
        request.setOrderId(orderId);

        /*
         * This controller is directly communicating with our producer
         * In an actual application, this wouldn't be the case. We would
         * instead call an 'OrderService' which contains business logic
         * for communicating with Producers/Repositories and other beans
         * as needed
         */
        orderProducer.sendOrderEvent(request);
        return ResponseEntity.ok("Event sent for order: " + orderId);
    }
}
