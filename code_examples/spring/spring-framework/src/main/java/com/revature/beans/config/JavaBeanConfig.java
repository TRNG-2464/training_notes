package com.revature.beans.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.revature.beans.config")
public class JavaBeanConfig {

    // Equivalent to the <constructor-arg> style bean
    @Bean
    public JavaBean javaBean1() {
        return new JavaBean(1000, "First Bean", "First Java-Configured Bean");
    }

    // Equivalent to the <property> style bean
    @Bean
    public JavaBean javaBean2() {
        JavaBean bean = new JavaBean();
        bean.setId(1001);
        bean.setName("Mr Bean");
        bean.setData("Second Java-Configured Bean | Using setters instead of constructor args");
        return bean;
    }

    /*
        This inserts a dependency - note, this doesn't create a second instance of javaBean1
        beans are singleton instances, so calling javaBean1() again returns the same bean
     */
    @Bean
    public DependentBean dependentBean() {
        return new DependentBean( javaBean1() );
    }
}
