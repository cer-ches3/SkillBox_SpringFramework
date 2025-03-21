package com.example.spring_web.repository;

import com.example.spring_web.model.Task;

import java.util.List;
import java.util.Optional;

public interface TaskRepository {
    List<Task> findAll();

    Optional<Task> findById(Long id);

    Task save(Task task);

    Task update(Task task);

    void deleteById(Long id);

    void batchInsert(List<Task> tasks);
}
