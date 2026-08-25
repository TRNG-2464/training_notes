package com.revature.beans.di;

import org.springframework.stereotype.Component;

@Component
public class DataProvider {
    public String fetch(String service) {
        return "Data From DataProvider Bean, fetched by: " + service;
    }
}
