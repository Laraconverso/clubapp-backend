package com.APIclubApp.clubApp.service;

import com.APIclubApp.clubApp.dto.CoachDTO;
import com.APIclubApp.clubApp.model.Coach;

import java.util.List;

public interface CoachService {
    public List<CoachDTO> listAllCoaches();

    Coach saveCoach(CoachDTO coach);

    CoachDTO getCoachById(Long id);

    Coach updateCoach(CoachDTO coach);



    void deleteCoach(Long id);
}
