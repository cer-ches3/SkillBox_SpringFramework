package com.example.bootstartdemo.configuration.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

@ConfigurationProperties(prefix = "background-executor")
@Getter
@Setter
public class BackgroundTaskProperties {
    private Boolean enabled;

    private String executor;
    private int taskSize;

    @NestedConfigurationProperty
    private CroneExecutorProperties cron;

    @NestedConfigurationProperty
    private TimeExecutorProperties time;
}
