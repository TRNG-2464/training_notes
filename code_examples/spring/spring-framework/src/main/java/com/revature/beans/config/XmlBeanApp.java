package com.revature.beans.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class XmlBeanApp {

    public static void main(String[] args) {
        ApplicationContext appContext = new ClassPathXmlApplicationContext("beans.xml");


        /*
         * the getBean method takes a string argument with a string
         * value matching the name of a bean configured in our xml
         * file configuration. The name of the bean should match the
         * 'id' attribute of the <bean> tag
         *
         * The 'getBean' method, when supplied with the Bean id, will
         * return a generic Object...You must explicitly cast the
         * Object to your specific type, or use the overloaded method
         * and pass the class as a second argument.
         */
        XmlBean bean1 = (XmlBean) appContext.getBean("XmlBean1");
        System.out.println(bean1);

        XmlBean bean2 = appContext.getBean("XmlBean2", XmlBean.class);
        System.out.println(bean2);
    }
}
