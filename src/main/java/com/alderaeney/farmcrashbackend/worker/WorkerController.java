package com.alderaeney.farmcrashbackend.worker;

import java.util.List;
import java.util.Optional;

import com.alderaeney.farmcrashbackend.worker.exceptions.WorkerNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/worker")
public class WorkerController {

    private final WorkerService workerService;

    @Autowired
    public WorkerController(WorkerService workerService) {
        this.workerService = workerService;
    }

    @GetMapping(path = "all")
    public List<Worker> getAllWorkers() {
        return workerService.getAllWorkers();
    }

    @GetMapping(path = "{workerId}")
    public Worker getWorkerById(@PathVariable("workerId") Long id) {
        Optional<Worker> worker = workerService.getWorkerById(id);
        if (worker.isPresent()) {
            return worker.get();
        }
        throw new WorkerNotFoundException(id);
    }

}
