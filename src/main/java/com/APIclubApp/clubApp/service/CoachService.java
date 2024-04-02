package com.APIclubApp.clubApp.service;

import com.APIclubApp.clubApp.dto.CoachBasicDTO;
import com.APIclubApp.clubApp.dto.CoachCategoryDTO;
import com.APIclubApp.clubApp.dto.CoachDTO;
import com.APIclubApp.clubApp.model.Coach;

import java.util.List;
import java.util.Optional;

public interface CoachService {
    public List<Coach> listAllCoaches();
    public List<CoachBasicDTO> listAllCoachesBasic();

    Coach saveCoach(CoachDTO coach);

    Coach getCoachById(Long id);

    Coach updateCoach(CoachDTO coach);

    void deleteCoach(Long id);

    Long getCoachesCount();

    Coach getCoachByDNI(String dni);

    Optional<Coach> getCoachByDNICat(String dni);
}
