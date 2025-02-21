package org.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class DefaultEnvPrinter implements EnvPrinter {

    @Value("${spring.profiles.active}")
    private String env;

    @Override
    public void printEnv() {
        System.out.println("Calling DefaultEnvPrinter printEnv");
        System.out.println("DefaultEnvPrinter env is: " + env);
    }
}
