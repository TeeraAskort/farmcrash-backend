package com.alderaeney.farmcrashbackend.worker;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkerService {

    private final WorkerRepository repository;

    @Autowired
    public WorkerService(WorkerRepository repository) {
        this.repository = repository;
    }

    public List<Worker> getAllWorkers() {
        return repository.findAll();
    }

    public Optional<Worker> getWorkerById(Long id) {
        return repository.findById(id);
    }

}
