package com.example.bootstartdemo.core;

public interface BackgroundTaskExecutor {
    void schedule(String taskId, Runnable task);
}
