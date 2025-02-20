package org.example;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Scope("prototype")
@Component
public class PrototypeComponent {
    private Instant creationTime;

    public PrototypeComponent() {
        System.out.println("Prototype component created");
        creationTime = Instant.now();
        printCreationTime();
    }

    public void printCreationTime() {
        System.out.println("Prototype component created at: " + creationTime);
    }
}
