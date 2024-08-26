package com.spring.examples.dependency_injection.constructor_DI_XML;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ExecutionAutowireDIConfiguration {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        Property property = context.getBean(Property.class);
        property.printDetails();
    }
}
