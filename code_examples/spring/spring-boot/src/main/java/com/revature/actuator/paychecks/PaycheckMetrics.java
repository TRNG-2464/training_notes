package com.revature.actuator.paychecks;

import com.revature.basics.repository.PaycheckRepository;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Component;

@Component
public class PaycheckMetrics {

    /*
     * Gauge tracks the current number of rows of Paycheck records
     * in our database. Unlike the 'Counter' - this value can go
     * up or down over time
     */
    public PaycheckMetrics(MeterRegistry meterRegistry, PaycheckRepository paycheckRepository) {
        Gauge.builder("paychecks.count", paycheckRepository, PaycheckRepository::count)
                .description("Current number of Paycheck records in the database")
                .register(meterRegistry);
    }
}
