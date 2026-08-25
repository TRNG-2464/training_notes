package com.revature.beans.di;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


public class AutowireDemoApp {

    @Configuration
    @ComponentScan(basePackages = "com.revature.beans.di")
    static class ScanConfig { }

    public static void main(String[] args) {
        ApplicationContext appContext = new AnnotationConfigApplicationContext(ScanConfig.class);

        ConstructorInjectedService cis = appContext.getBean(ConstructorInjectedService.class);
        SetterInjectedService sis = appContext.getBean(SetterInjectedService.class);
        FieldInjectedService fis = appContext.getBean(FieldInjectedService.class);

        cis.report();
        sis.report();
        fis.report();
    }
}
