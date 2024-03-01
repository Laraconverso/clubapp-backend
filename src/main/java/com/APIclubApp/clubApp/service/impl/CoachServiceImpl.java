package com.APIclubApp.clubApp.service.impl;

import com.APIclubApp.clubApp.dto.CoachDTO;
import com.APIclubApp.clubApp.model.Coach;
import com.APIclubApp.clubApp.repository.CoachRepository;
import com.APIclubApp.clubApp.service.CoachService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CoachServiceImpl implements CoachService {

    @Autowired
    private CoachRepository coachRepository;

    @Autowired
    ObjectMapper objectMapper;

    @Override
    public List<CoachDTO> listAllCoaches() {
        //return coachRepository.findAll();
        List<Coach> allCoachs = coachRepository.findAll();
        List<CoachDTO> allCoachsDto = new ArrayList<>();
        for (Coach coach: allCoachs)
            allCoachsDto.add(objectMapper.convertValue(coach, CoachDTO.class));

        return allCoachsDto;
    }

    @Override
    public Coach saveCoach(CoachDTO coach) {
        Coach newCoach = objectMapper.convertValue(coach, Coach.class);
        return coachRepository.save(newCoach);
    }

    @Override
    public CoachDTO getCoachById(Long id) {
        Optional<Coach> coachOptional = coachRepository.findById(id);
        return objectMapper.convertValue(coachOptional, CoachDTO.class);
    }

    @Override
    public Coach updateCoach(CoachDTO coach) {
        Coach editCoach= objectMapper.convertValue(coach, Coach.class);
        return coachRepository.save(editCoach);
    }



    @Override
    public void deleteCoach(Long id) {
        coachRepository.deleteById(id);
    }
}
