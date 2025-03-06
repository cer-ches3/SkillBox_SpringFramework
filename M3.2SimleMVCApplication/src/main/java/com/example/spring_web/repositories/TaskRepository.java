package com.example.spring_web.repositories;

import com.example.spring_web.models.Task;

import java.util.List;
import java.util.Optional;

public interface TaskRepository {
    List<Task> findAll();
    Optional<Task> findById(Long id);
    Task save (Task task);
    Task update(Task task);
    void deleteById(Long id);
}
