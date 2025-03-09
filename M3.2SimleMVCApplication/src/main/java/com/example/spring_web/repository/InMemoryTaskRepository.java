package com.example.spring_web.repository;

import com.example.spring_web.model.Task;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@Slf4j
public class InMemoryTaskRepository implements TaskRepository {
    private final List<Task> tasks = new ArrayList<>();

    @Override
    public List<Task> findAll() {
        log.debug("Call findAll in InMemoryTaskRepository");

        return tasks;
    }

    @Override
    public Optional<Task> findById(Long id) {
        log.debug("Call findById in InMemoryTaskRepository. ID is: {}", id);

        return tasks.stream().filter(task -> task.getId() == id).findFirst();
    }

    @Override
    public Task save(Task task) {
        log.debug("Call save in InMemoryTaskRepository. Task is {}", task);

        task.setId(System.currentTimeMillis());
        tasks.add(task);

        return task;
    }

    @Override
    public Task update(Task task) {
        log.debug("Call update in InMemoryTaskRepository. Task is {}", task);

        Task excistedTask = findById(task.getId()).orElse(null);
        if (excistedTask != null) {
            excistedTask.setPriority(task.getPriority());
            excistedTask.setTitle(task.getTitle());
            excistedTask.setDescription(task.getDescription());
        }
        return excistedTask;
    }

    @Override
    public void deleteById(Long id) {
        log.debug("Call deleteById in InMemoryTaskRepository. ID is: {}", id);

        findById(id).ifPresent(tasks::remove);
    }

    @Override
    public void batchInsert(List<Task> tasks) {
        this.tasks.addAll(tasks);
    }
}
