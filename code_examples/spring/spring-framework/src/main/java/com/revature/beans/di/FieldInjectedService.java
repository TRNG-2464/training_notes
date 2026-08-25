package com.revature.beans.di;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FieldInjectedService {

    @Autowired
    private DataProvider dataProvider;

    public void report() {
        System.out.println("dataProvider.fetch(): " + dataProvider.fetch("[Field Injected Service]"));
    }
}
