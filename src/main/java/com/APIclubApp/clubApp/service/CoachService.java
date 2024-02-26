package com.APIclubApp.clubApp.service;

import com.APIclubApp.clubApp.model.Coach;

import java.util.List;

public interface CoachService {
    public List<Coach> listAllCoaches();

    Coach saveCoach(Coach coach);

    Coach getCoachById(Long id);

    Coach updateCoach(Coach coach);

    Coach updateCoach(Long id, Coach coachDetails);

    void deleteCoach(Long id);
}
