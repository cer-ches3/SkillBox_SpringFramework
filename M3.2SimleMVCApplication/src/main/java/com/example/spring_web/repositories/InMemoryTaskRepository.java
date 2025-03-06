package com.example.spring_web.repositories;

import com.example.spring_web.models.Task;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@Slf4j
public class InMemoryTaskRepository implements TaskRepository{
    private final List<Task> tasks = new ArrayList<>();

    @Override
    public List<Task> findAll() {
        log.debug("Call findAll from repository");
        return tasks;
    }

    @Override
    public Optional<Task> findById(Long id) {
        log.debug("Call findById from repository");
        return tasks.stream()
                .filter(t -> t.getId() ==(id))
                .findFirst();
    }

    @Override
    public Task save(Task task) {
        log.debug("Call save from repository");
        task.setId(System.currentTimeMillis());
        tasks.add(task);
        return task;
    }

    @Override
    public Task update(Task task) {
        log.debug("Call update from repository");
        Task existTask = findById(task.getId()).orElse(null);
        if (existTask != null) {
            existTask.setTitle(task.getTitle());
            existTask.setDescription(task.getDescription());
            existTask.setPriority(task.getPriority());
        }
        return existTask;
    }

    @Override
    public void deleteById(Long id) {
        log.debug("Call deleteById from repository");
        findById(id).ifPresent(tasks::remove);
    }
}
