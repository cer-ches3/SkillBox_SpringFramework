package com.example.bootconfigdemo.config;

import com.example.bootconfigdemo.EventQueue;
import com.example.bootconfigdemo.EventQueueWorker;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnBean(EventQueue.class)
public class EventQueueConfiguration {

    @Bean
    public EventQueueWorker eventQueueWorker(EventQueue eventQueue, ApplicationEventPublisher publisher) {
        return new EventQueueWorker(eventQueue, publisher);
    }
}
