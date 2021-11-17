package com.alderaeney.farmcrashbackend.worker;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkerRepository extends JpaRepository<Worker, Long> {
    List<Worker> getAllWorkerByHired(Hired hired);
}
