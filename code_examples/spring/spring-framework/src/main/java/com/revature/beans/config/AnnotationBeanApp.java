package com.revature.beans.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

public class AnnotationBeanApp {

    // Bootstraps component scanning
    @Configuration
    @ComponentScan(basePackages = "com.revature.beans.config")
    static class ScanConfig { }

    public static void main(String[] args) {
        ApplicationContext appContext = new AnnotationConfigApplicationContext(ScanConfig.class);

        AnnotationBean bean = appContext.getBean("myAnnotationBean", AnnotationBean.class);
        System.out.println(bean);
    }
}