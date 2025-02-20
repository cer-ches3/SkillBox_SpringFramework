package org.example;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Scope("singleton")
@Component
public class FirstSingleton {
    private Instant creationTime;

    public FirstSingleton() {
        System.out.println("First Singleton created");
        creationTime = Instant.now();
        printCreationTime();
    }

    public void printCreationTime() {
        System.out.println("First Singleton created at: " + creationTime);
    }
}
