package com.revature.expanded.health.paychecks;

import com.revature.expanded.repository.PaycheckRepository;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Component;

@Component
public class PaycheckMetrics {

    public PaycheckMetrics(MeterRegistry meterRegistry, PaycheckRepository paycheckRepository) {
        Gauge.builder("paychecks.count", paycheckRepository, d -> paycheckRepository.count())
                .description("Current number of Paycheck records in the database")
                .register(meterRegistry);
    }
}
