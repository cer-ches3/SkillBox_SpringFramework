package com.example.spring_web.listener;

import com.example.spring_web.models.Task;
import com.example.spring_web.services.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class DBTaskCreator {
    private final TaskService taskService;

    @EventListener(ApplicationStartedEvent.class)
    public void createTask(){
        log.debug("Call createTask from DBTaskCreator");
        List<Task> tasks = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            int value = i + 1;
            Task task = new Task();
            task.setId((long) value);
            task.setTitle("Title: " + value);
            task.setDescription("Description: " + value);
            task.setPriority(value);

            tasks.add(task);
        }
        taskService.batchInsert(tasks);
    }
}
