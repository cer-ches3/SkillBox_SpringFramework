package com.example.spring_web.services;

import com.example.spring_web.models.Task;
import com.example.spring_web.repositories.TaskRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class TaskServiceImpl implements TaskService{
    private final TaskRepository taskRepository;

    @Override
    public List<Task> findAll() {
        log.debug("Call findAll from service");
        return taskRepository.findAll();
    }

    @Override
    public Task findById(Long id) {
        log.debug("Call findById from service");
        return taskRepository.findById(id).orElse(null);
    }

    @Override
    public Task save(Task task) {
        log.debug("Call save from service");
        return taskRepository.save(task);
    }

    @Override
    public Task update(Task task) {
        log.debug("Call update from service");
        return taskRepository.update(task);
    }

    @Override
    public void deleteById(Long id) {
        log.debug("Call deleteById from service");
        taskRepository.deleteById(id);
    }

    @Override
    public void batchInsert(List<Task> tasks) {
        log.debug("Call batchInsert from service");
        taskRepository.batchInsert(tasks);
    }
}
