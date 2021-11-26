package com.alderaeney.farmcrashbackend.task;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    private final TaskRepository repository;

    @Autowired
    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public Optional<Task> getTaskById(Long id) {
        return repository.findById(id);
    }

    public List<Task> getAllTasks() {
        return repository.findAllByAssigned(false);
    }

    public void insertTask(Task task) {
        repository.save(task);
    }

}
