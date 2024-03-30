package com.APIclubApp.clubApp.service.impl;

import com.APIclubApp.clubApp.dto.CoachDTO;
import com.APIclubApp.clubApp.exception.NotFoundException;
import com.APIclubApp.clubApp.model.*;
import com.APIclubApp.clubApp.repository.CategoryRepository;
import com.APIclubApp.clubApp.repository.ClubRepository;
import com.APIclubApp.clubApp.repository.CoachRepository;
import com.APIclubApp.clubApp.security.usersecurity.model.RoleEntity;
import com.APIclubApp.clubApp.security.usersecurity.repository.RoleSecurityRepository;
import com.APIclubApp.clubApp.service.CoachService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoachServiceImpl implements CoachService {

    @Autowired
    private CoachRepository coachRepository;

    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    private ClubRepository clubRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private RoleSecurityRepository roleSecurityRepository;

    @Override
    public List<Coach> listAllCoaches() {
        return coachRepository.findAll();
        /*List<Coach> allCoachs = coachRepository.findAll();
        List<CoachDTO> allCoachsDto = new ArrayList<>();
        for (Coach coach: allCoachs)
            allCoachsDto.add(objectMapper.convertValue(coach, CoachDTO.class));

        return allCoachsDto;*/
    }

    @Override
    public Coach saveCoach(CoachDTO coach) {
        Coach newCoach = objectMapper.convertValue(coach, Coach.class);
        // Fetch the Category object from the database by its ID
        //Category category = categoryRepository.findById(coach.getCategoryId())
                //.orElseThrow(()->new RuntimeException("Category not found"));
        //Fetch the Club object from the dataase by its ID
        Club club = clubRepository.findById(coach.getClubId())
                .orElseThrow(()->new RuntimeException("Club not found"));
        //Fetch the Role object from the dataase by its ID
        RoleEntity roleEntity = roleSecurityRepository.findByRolename("COACH")
                .orElseThrow(()->new RuntimeException("Role not found"));

        //newCoach.setCategory(category);
        newCoach.setClub(club);
        newCoach.setRole(roleEntity);

        return coachRepository.save(newCoach);
    }

    @Override
    public Coach getCoachById(Long id) {
        /*Optional<Coach> coachOptional = coachRepository.findById(id);
        return objectMapper.convertValue(coachOptional, CoachDTO.class);*/
        return coachRepository.findById(id).get();
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

    @Override
    public Long getCoachesCount(){
        return coachRepository.count();
    }

    @Override
    public Coach getCoachByDNI(String dni) {
        Coach coach = coachRepository.findByUserDni(dni);
        if (coach == null) {
            throw new NotFoundException("Coach not found with DNI: " + dni);
        }
        return coach;
    }
}
