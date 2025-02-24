package com.example.bootconfigdemo.event;

import com.example.bootconfigdemo.Event;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class EventHolder extends ApplicationEvent {
    public final Event event;

    public EventHolder(Object source, Event event) {
        super(source);
        this.event = event;
    }
}
