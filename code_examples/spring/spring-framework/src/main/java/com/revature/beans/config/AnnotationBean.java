package com.revature.beans.config;

import org.springframework.stereotype.Component;

@Component("myAnnotationBean")
public class AnnotationBean {
    private int id;
    private String name;
    private String data;

    /*
     *  Constructor injection - Spring calls this automatically since there's
     *  only one constructor (no @Autowired needed on it in modern Spring).
     */
    public AnnotationBean() {
        super();
//        System.out.println("No-Args Constructor Called - MyAnnotationBean");
        this.id = 2000;
        this.name = "Annotation Bean";
        this.data = "First Annotation-Configured Bean";
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getData() { return data; }
    public void setData(String data) { this.data = data; }

    @Override
    public String toString() {
        return "MyAnnotationBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}