package com.APIclubApp.clubApp.service.impl;

import com.APIclubApp.clubApp.model.Club;
import com.APIclubApp.clubApp.repository.ClubRepository;
import com.APIclubApp.clubApp.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClubServiceImpl implements ClubService {

    @Autowired
    public ClubRepository clubRepository;

    @Override
    public List<Club> listAllClubes() {
        return clubRepository.findAll();
    }

    @Override
    public Club saveClub(Club club) {
        return clubRepository.save(club);
    }

    @Override
    public Club getClubById(Long id) {return clubRepository.findById(id).get();}

    @Override
    public Club updateClub(Club club) {
        return clubRepository.save(club);
    }

    @Override
    public void deleteClub(Long id) {
        clubRepository.deleteById(id);
    }
}
