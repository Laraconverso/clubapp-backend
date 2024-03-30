package com.APIclubApp.clubApp.service.impl;

import com.APIclubApp.clubApp.dto.ClubDTO;
import com.APIclubApp.clubApp.exception.AlreadyExistsException;
import com.APIclubApp.clubApp.exception.NotFoundException;
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
        Optional<Club> existingClub= clubRepository.findByClubName(club.getClubName());
        if (existingClub.isPresent()) {
            throw new AlreadyExistsException("Club already exists with name: " + club.getClubName());
        }
        Club newClub=  objectMapper.convertValue(club, Club.class);
        return clubRepository.save(newClub);
    }

    @Override
    public ClubDTO getClubById(Long id) {

        //return clubRepository.findById(id).orElse(null);
        Optional<Club> searchedClub=clubRepository.findById(id);
        Club club = searchedClub.orElseThrow(() -> new NotFoundException("Club not found with ID: " + id));
        return objectMapper.convertValue(club,ClubDTO.class);
    }

    @Override
    public Club updateClub(ClubDTO club) {
        Club existingClub = clubRepository.findById(club.getClubId())
                .orElseThrow(() -> new NotFoundException("Club not found with ID: " + club.getClubId()));

        Optional<Club> existingClubWithSameName = clubRepository.findByClubName(club.getClubName());
        if (existingClubWithSameName.isPresent()
                && !existingClubWithSameName.get().getClubId().equals(existingClub.getClubId())) {
            throw new AlreadyExistsException("Club already exists with name: " + club.getClubName());
        }

        Club editClub=  objectMapper.convertValue(club, Club.class);

        existingClub.setClubName(editClub.getClubName());
        existingClub.setClubDescription(editClub.getClubDescription());
        existingClub.setClubLogo(editClub.getClubLogo());

        return clubRepository.save(editClub);
    }

    @Override
    public void deleteClub(Long id) {
        if (clubRepository.existsById(id)){
            clubRepository.deleteById(id);
        } else {
            throw new NotFoundException("Club not found with ID: " + id);
        }
    }
}

