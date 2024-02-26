package com.APIclubApp.clubApp.service.impl;

import com.APIclubApp.clubApp.model.Statistic;
import com.APIclubApp.clubApp.repository.StatisticRepository;
import com.APIclubApp.clubApp.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatisticServiceImpl implements StatisticService {
    @Autowired
    private StatisticRepository statisticRepository;

    @Override
    public List<Statistic> listAllStatistics() {
        return statisticRepository.findAll();
    }

    @Override
    public Statistic saveStatistic(Statistic statistic) {
        return statisticRepository.save(statistic);
    }

    @Override
    public Statistic getStatisticById(Long id) {
        return statisticRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteStatistic(Long id) {
        statisticRepository.deleteById(id);
    }
}
