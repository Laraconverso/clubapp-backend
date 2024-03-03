package com.APIclubApp.clubApp.service.impl;

import com.APIclubApp.clubApp.dto.CategoryDTO;
import com.APIclubApp.clubApp.dto.ClubDTO;
import com.APIclubApp.clubApp.model.Category;
import com.APIclubApp.clubApp.model.Club;
import com.APIclubApp.clubApp.repository.ClubRepository;
import com.APIclubApp.clubApp.service.ClubService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClubServiceImpl implements ClubService {

    @Autowired
    private ClubRepository clubRepository;

    @Autowired
    ObjectMapper objectMapper;

    @Override
    public List<ClubDTO> listAllClubs() {

        //return clubRepository.findAll();
        List<Club> allClubs = clubRepository.findAll();
        List<ClubDTO> allClubsDTO = new ArrayList<>();// preguntar a Lara pq da error con Hashset
        for(Club club: allClubs)
            allClubsDTO.add(objectMapper.convertValue(club,ClubDTO.class));

        return allClubsDTO;
    }

    @Override
    public Club saveClub(ClubDTO club) {

        Club newClub=  objectMapper.convertValue(club, Club.class);
        return clubRepository.save(newClub);
    }

    @Override
    public ClubDTO getClubById(Long id) {

        //return clubRepository.findById(id).orElse(null);
        Optional<Club> clubBuscado=clubRepository.findById(id);
        return objectMapper.convertValue(clubBuscado,ClubDTO.class);
    }

    @Override
    public Club updateClub(ClubDTO club) {
        Club editClub=  objectMapper.convertValue(club, Club.class);
        return clubRepository.save(editClub);
    }

    @Override
    public void deleteClub(Long id) {
        clubRepository.deleteById(id);
    }
}

