package com.revature.beans.lifecycle;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class LifecycleBean implements BeanNameAware, ApplicationContextAware, InitializingBean, DisposableBean {

    private LifecycleDataProvider lifecycleDataProvider;

    private String beanName;
    private ApplicationContext appContext;

    /*
     * Stage 1: Instantiation
     * Constructor Injections happens first, before any of the Aware interfaces
     * or lifecycle callbacks are called. By the time any other method in this
     * class runs, the Data Provider is already guaranteed to be set
     */
    public LifecycleBean(LifecycleDataProvider lifecycleDataProvider) {
        this.lifecycleDataProvider = lifecycleDataProvider;
        System.out.println("[Stage 1]\nConstructor Called: LifecycleDataProvider Injected");
    }

    /*
     * Stage 2: Aware Interfaces (after properties are populated).
     * BeanNameAware gives this bean access to the name it was registered under
     * in the container. This can be useful for logging/debugging
     */
    @Override
    public void setBeanName(String name) {
        this.beanName = name;
        System.out.println("[Stage 2]\nBeanNameAware: bean registered under name '" + name + "'");
    }

    /*
     * ApplicationContextAware gives this bean a reference to the container
     * itself. This lets the bean look up Other beans manually, if needed.
     * Note: This should be used sparingly in practice, since it re-introduces
     * a dependency on the Spring container into your class
     */
    @Override
    public void setApplicationContext(ApplicationContext appContext) {
        this.appContext = appContext;
        System.out.println("[Stage 2]\nApplicationContextAware: ApplicationContext reference set");
    }

    /*
     * Stage 3: Initialization [Callbacks in order]
     */
    @PostConstruct
    public void postConstructInit() {
        System.out.println("[Stage 3]\n1. @PostConstruct");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("[Stage 3]\n2. afterPropertiesSet()");
    }

    // Custom init method - wired in @Configuration on the Config class - see 'LifecycleAppConfig.java'
    public void customInit() {
        System.out.println("[Stage 3]\n3. customInit method");
    }

    // See 'LifecycleApp.java' for 'Stage 4'

    /*
     * Stage 5: Destruction [Callbacks in order] | Singleton-scoped beans only!
     */
    @PreDestroy
    public void preDestroyCleanup() {
        System.out.println("[Stage 5]\n1. @PreDestroy cleanup (ex: flushing pending writes)");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("[Stage 5]\n2. destroy() to release resources");
    }

    // Custom destroy methods - wired in @Configuration on the Config class - see 'LifecycleAppConfig.java
    public void customDestroy() {
        System.out.println("[Stage 5]\n3. customDestroy method");
    }
}
