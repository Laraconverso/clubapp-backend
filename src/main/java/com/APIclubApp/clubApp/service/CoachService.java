package com.APIclubApp.clubApp.service;

import com.APIclubApp.clubApp.dto.CoachBasicDTO;
import com.APIclubApp.clubApp.dto.CoachDTO;
import com.APIclubApp.clubApp.model.Coach;

import java.util.List;

public interface CoachService {
    public List<CoachDTO> listAllCoaches();
    public List<CoachBasicDTO> listAllCoachesBasic();

    Coach saveCoach(CoachDTO coach);

    Coach getCoachById(Long id);

    Coach updateCoach(CoachDTO coach);

    void deleteCoach(Long id);

    Long getCoachesCount();
}
