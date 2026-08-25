package com.revature.beans.config;

public class JavaBean {
    private int id;
    private String name;
    private String data;

    public JavaBean() { super(); }

    public JavaBean(int id, String name, String data) {
        super();
        System.out.println("Parameterized Constructor Called - JavaBean");
        this.id = id;
        this.name = name;
        this.data = data;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getData() { return data; }
    public void setData(String data) { this.data = data; }

    @Override
    public String toString() {
        return "MyJavaBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}