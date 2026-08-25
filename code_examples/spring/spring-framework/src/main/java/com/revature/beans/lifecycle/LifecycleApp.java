package com.revature.beans.lifecycle;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class LifecycleApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext(LifecycleAppConfig.class);

        LifecycleBean lifecycleBean = appContext.getBean(LifecycleBean.class);
        System.out.println("[Stage 4]Bean retrieved and ready for use");

        /*
         * Note: Destruction callback will not run automatically when a java program ends
         * they specifically run the ApplicationContext itself is closed. Calling close()
         * on the appContext is what triggers the destroy method calls
         */
        appContext.close();
    }
}
