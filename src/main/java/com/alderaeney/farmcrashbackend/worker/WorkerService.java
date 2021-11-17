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

    public List<Worker> getAllNotHiredWorkers() {
        return repository.getAllWorkerByHired(Hired.NOT_HIRED);
    }

    public Optional<Worker> getWorkerById(Long id) {
        return repository.findById(id);
    }

    public void insertWorker(Worker worker) {
        repository.save(worker);
    }

    public void removeWorker(Worker worker) {
        repository.delete(worker);
    }

}
