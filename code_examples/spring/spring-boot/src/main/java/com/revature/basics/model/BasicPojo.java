package com.revature.basics.model;

import org.springframework.stereotype.Component;

/*
 * This bean has a lot of boilerplate code - compare it
 * to the 'BetterPojo.java' file
 */
@Component
public class BasicPojo {
    private Integer id;
    private String name;
    private boolean isActive;

    public BasicPojo() {
    }

    public BasicPojo(Integer id, String name, boolean isActive) {
        this.id = id;
        this.name = name;
        this.isActive = isActive;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "SimpleBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}
