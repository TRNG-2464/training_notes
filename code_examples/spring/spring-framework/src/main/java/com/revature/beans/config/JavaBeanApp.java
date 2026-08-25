package com.revature.beans.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class JavaBeanApp {

    public static void main(String[] args) {
        ApplicationContext appContext = new AnnotationConfigApplicationContext(JavaBeanConfig.class);

        JavaBean bean1 = appContext.getBean("javaBean1", JavaBean.class);
        System.out.println(bean1);

        JavaBean bean2 = appContext.getBean("javaBean2", JavaBean.class);
        System.out.println(bean2);

        DependentBean dependent = appContext.getBean("dependentBean", DependentBean.class);
        System.out.println(dependent);

        // The following proves that the internal reference is the same singleton:
        System.out.println("Same instance? " + (bean1 == dependent.getSource()));
    }
}
