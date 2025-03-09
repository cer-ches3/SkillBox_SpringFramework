package com.example.spring_web.services;

import com.example.spring_web.models.Task;

import java.util.List;
import java.util.Optional;

public interface TaskService {
    List<Task> findAll();
    Task findById(Long id);
    Task save (Task task);
    Task update(Task task);
    void deleteById(Long id);
    void batchInsert(List<Task> tasks);
}
