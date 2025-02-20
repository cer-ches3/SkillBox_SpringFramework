package org.example;

import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Scope("singleton")
@Component
@Lazy
public class SecondSingleton {
    private Instant creationTime;
    private final PrototypeComponent component;

    public SecondSingleton(PrototypeComponent prototypeSingleton) {
        System.out.println("Second Singleton created");
        creationTime = Instant.now();
        printCreationTime();
        this.component = prototypeSingleton;
        component.printCreationTime();
    }

    public void printCreationTime() {
        System.out.println("Second Singleton created at: " + creationTime);
    }
}
