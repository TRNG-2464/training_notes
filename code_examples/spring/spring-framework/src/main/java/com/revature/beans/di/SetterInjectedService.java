package com.revature.beans.di;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetterInjectedService {
    private DataProvider dataProvider;

    @Autowired
    public void setDataProvider(DataProvider dataProvider) {
        System.out.println("SetterInjectedService - Setter Called | Setter Injection");
        this.dataProvider = dataProvider;
    }

    public void report() {
        System.out.println("dataProvider.fetch(): " + dataProvider.fetch("[Setter Injected Service]"));
    }
}
