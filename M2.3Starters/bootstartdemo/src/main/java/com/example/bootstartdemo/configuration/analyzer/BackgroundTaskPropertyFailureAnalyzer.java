package com.example.bootstartdemo.configuration.analyzer;

import com.example.exception.BackgroundTaskPropertiesException;
import org.springframework.boot.diagnostics.AbstractFailureAnalyzer;
import org.springframework.boot.diagnostics.FailureAnalysis;

import java.text.MessageFormat;

public class BackgroundTaskPropertyFailureAnalyzer extends AbstractFailureAnalyzer<BackgroundTaskPropertiesException> {

    @Override
    protected FailureAnalysis analyze(Throwable rootFailure, BackgroundTaskPropertiesException cause) {
        return new FailureAnalysis(MessageFormat.format
                ("Exception whn try to set property: {}", cause.getMessage()), "set-application-properties", cause);
    }
}
