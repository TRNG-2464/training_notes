package com.revature.beans.di;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConstructorInjectedService {
    /*
     * Constructor injection is the preferred method of injection for
     * bean dependencies, because it allows you to declare the dependencies
     * as final variable references - this ensures that you WILL perform
     * the injection (i.e. you can't accidentally forget to use '@Autowired'
     * in your class)
     */
    private final DataProvider dataProvider;

    @Autowired
    public ConstructorInjectedService(DataProvider dataProvider) {
        System.out.println("ConstructorInjectedService - Constructor Called | Constructor Injection");
        this.dataProvider = dataProvider;
    }

    public void report() {
        System.out.println("dataProvider.fetch(): " + dataProvider.fetch("[Constructor Injected Service]"));
    }
}
