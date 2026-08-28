package com.revature.basics.spel;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SpELService {

    /*
     * Simple Literal SpEL expression
     *
     * The @Value attribute is a common usecase for SpEL.
     * Note: the following is not very useful, since it is
     * just a static value...
     */
    @Value("#{5 * 20}") // 100
    private int basePriceInCents;

    /*
     * A more common, and useful application of SpEL
     * This would read the "region" property off the
     * bean named "storeConfig"
     */
    @Value("#storeConfig.region")
    private String region;
}

// The following is used as part of the SpEL service example above
@Getter
@Component("storeConfig")
class StoreConfig {

    /*
     * Using a '$' you can perform a reference substitution
     * This specifically tells Spring to look for a property
     * in the configuration of the project (application.properties)
     * and provide the value given there.
     */
    @Value("${app.store.region}")
    private String region;

    @Value("${app.store.name}")
    private String storeName;
}
