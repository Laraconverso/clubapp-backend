package com.APIclubApp.clubApp.service;

import com.APIclubApp.clubApp.model.Club;

import java.util.List;

public interface ClubService {
    List<Club> listAllClubs();

    Club saveClub(Club club);

    Club getClubById(Long id);

    Club updateClub(Club club);

    void deleteClub(Long id);
}
