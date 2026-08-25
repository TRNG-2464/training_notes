package com.revature.beans.lifecycle;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.revature.beans.lifecycle")
public class LifecycleAppConfig {
    @Bean(initMethod = "customInit", destroyMethod = "customDestroy")
    public LifecycleBean lifecycleBean(LifecycleDataProvider lifecycleDataProvider) {
        return new LifecycleBean(lifecycleDataProvider);
    }
}
