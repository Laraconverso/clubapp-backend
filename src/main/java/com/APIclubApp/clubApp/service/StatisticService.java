package com.APIclubApp.clubApp.service;

import com.APIclubApp.clubApp.model.Statistic;

import java.util.List;

public interface StatisticService {
    List<Statistic> listAllStatistics();

    Statistic saveStatistic(Statistic statistic);

    Statistic getStatisticById(Long id);

    void deleteStatistic(Long id);
}
