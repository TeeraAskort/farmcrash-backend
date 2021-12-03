package com.alderaeney.farmcrashbackend.stats;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatsService {

    private final StatsRepository statsRepository;
    private final DataSetRepository dataSetRepository;

    @Autowired
    public StatsService(StatsRepository statsRepository, DataSetRepository dataSetRepository) {
        this.statsRepository = statsRepository;
        this.dataSetRepository = dataSetRepository;
    }

    public void saveStats(Stats stats) {
        dataSetRepository.saveAll(stats.getDatasets());
        statsRepository.save(stats);
    }
}
