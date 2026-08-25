package com.revature.beans.config;

public class DependentBean {
    private final JavaBean source;
    private String summary;

    public DependentBean(JavaBean source) {
        System.out.println("Parameterized Constructor Called - Dependent Bean | Dependency Injection");
        this.source = source;
        this.summary = "Derived from bean id=" + source.getId();
    }

    public JavaBean getSource() { return source; }

    public String getSummary() { return summary; }

    public void setSummary(String summary) { this.summary = summary; }

    @Override
    public String toString() {
        return "DependentBean{" +
                "source=" + source +
                ", summary='" + summary + '\'' +
                '}';
    }
}
