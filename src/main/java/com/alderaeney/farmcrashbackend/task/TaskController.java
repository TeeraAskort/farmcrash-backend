package com.alderaeney.farmcrashbackend.task;

import java.util.List;
import java.util.Optional;

import com.alderaeney.farmcrashbackend.task.exceptions.TaskNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/task")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping(path = "all")
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping(path = "{taskId}")
    public Task getTaskById(@PathVariable("taskId") Long id) {
        Optional<Task> task = taskService.getTaskById(id);
        if (task.isPresent()) {
            return task.get();
        }
        throw new TaskNotFoundException(id);
    }

}
