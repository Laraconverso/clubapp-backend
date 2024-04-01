package com.APIclubApp.clubApp.service.impl;

import com.APIclubApp.clubApp.dto.CoachBasicDTO;
import com.APIclubApp.clubApp.dto.CoachCategoryDTO;
import com.APIclubApp.clubApp.dto.CoachDTO;
import com.APIclubApp.clubApp.exception.NotFoundException;
import com.APIclubApp.clubApp.model.*;
import com.APIclubApp.clubApp.repository.CategoryRepository;
import com.APIclubApp.clubApp.repository.ClubRepository;
import com.APIclubApp.clubApp.repository.CoachRepository;
import com.APIclubApp.clubApp.repository.RoleRepository;
import com.APIclubApp.clubApp.service.CoachService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    private RoleRepository roleRepository;


    @Override
    public List<CoachDTO> listAllCoaches() {
        //return coachRepository.findAll();
        List<Coach> allCoaches = coachRepository.findAll();
        List<CoachDTO> allCoachesDto = new ArrayList<>();
        for (Coach coach: allCoaches)
            allCoachesDto.add(objectMapper.convertValue(coach, CoachDTO.class));

        return allCoachesDto;
    }

    @Override
    public List<CoachBasicDTO> listAllCoachesBasic() {
        List<Coach> allCoaches = coachRepository.findAll();
        return allCoaches.stream()
                .map(coach -> {
                    CoachBasicDTO coachBasicDTO = new CoachBasicDTO();
                    coachBasicDTO.setCoachNumber(coach.getCoachNumber());
                    coachBasicDTO.setUserName(coach.getUserName());
                    return coachBasicDTO;
                })
                .collect(Collectors.toList());
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
        Role role = roleRepository.findByRoleName("Coach")
                .orElseThrow(()->new RuntimeException("Role not found"));

        //newCoach.setCategory(category);
        newCoach.setClub(club);
        newCoach.setRole(role);

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

    @Override
    public Optional<Coach> getCoachByDNICat(String dni) {

        Optional<Coach> coach = coachRepository.findByUserDniCat(dni);
        if (!coach.isPresent()) { // Verificar si la opción contiene un valor
            throw new NotFoundException("Coach not found with DNI: " + dni);
        }
        return coach;

       /* Optional<Coach> coach = coachRepository.findByUserDniCat(dni);
        if (coach.isPresent()) {
            Coach coachObj = coach.get();
            System.out.println("Category ID: " + coachObj.getCategory().getCategoryId()); // Verifica si se obtiene la categoría
        }
        return coach;*/

    }
}
