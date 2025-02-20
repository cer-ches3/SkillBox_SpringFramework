package org.example;

public class Main {
    public static void main(String[] args) {
        BeanFactory beanFactory = new BeanFactory();
        beanFactory.registry(Injectable.class);
        beanFactory.registry(Source.class);

        Source source = beanFactory.getBean(Source.class);
        source.call();
    }
}