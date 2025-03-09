package com.example.spring_web.listener;

import com.example.spring_web.model.Task;
import com.example.spring_web.service.TaskService;
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
public class DatabaseTaskCreator {
    private final TaskService taskService;

    //@EventListener(ApplicationStartedEvent.class)
    public void createTaskData() {
        log.debug("Calling DatabaseTaskCreator-> createTaskData...");

        List<Task> tasks = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            int value = i + 1;
            Task task = new Task();
            task.setId((long) value);
            task.setTitle("Title " + value);
            task.setDescription("Description " + value);
            task.setPriority(value);
            tasks.add(task);
        }
        taskService.batchInsert(tasks);
    }
}
