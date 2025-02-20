package org.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("org.example")
public class AppConfig {

    @Bean
    public LogicClass simpleLogicClass() {
        return new LogicClass();
    }

    @Bean
    public LogicClass simpleLogicData() {
        return new LogicClass("logic class", 42);
    }
}
