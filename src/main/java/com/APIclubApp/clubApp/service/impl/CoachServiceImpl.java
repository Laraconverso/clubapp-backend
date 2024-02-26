package com.APIclubApp.clubApp.service.impl;

import com.APIclubApp.clubApp.model.Coach;
import com.APIclubApp.clubApp.repository.CoachRepository;
import com.APIclubApp.clubApp.service.CoachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CoachServiceImpl implements CoachService {

    @Autowired
    private CoachRepository coachRepository;

    @Override
    public List<Coach> listAllCoaches() {
        return coachRepository.findAll();
    }

    @Override
    public Coach saveCoach(Coach coach) {
        return coachRepository.save(coach);
    }

    @Override
    public Coach getCoachById(Long id) {
        Optional<Coach> coachOptional = coachRepository.findById(id);
        return coachOptional.orElse(null);
    }

    @Override
    public Coach updateCoach(Coach coach) {
        return null;
    }

    @Override
    public Coach updateCoach(Long id, Coach coachDetails) {
        Optional<Coach> coachOptional = coachRepository.findById(id);
        if (coachOptional.isPresent()) {
            Coach coach = coachOptional.get();
            //coach.setCoachName(coachDetails.getCoachName());

            return coachRepository.save(coach);
        } else {
            return null;
        }
    }

    @Override
    public void deleteCoach(Long id) {
        coachRepository.deleteById(id);
    }
}
