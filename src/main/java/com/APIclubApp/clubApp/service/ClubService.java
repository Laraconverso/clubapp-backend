package com.APIclubApp.clubApp.service;

import com.APIclubApp.clubApp.dto.ClubDTO;
import com.APIclubApp.clubApp.model.Club;

import java.util.List;

public interface ClubService {
    List<ClubDTO> listAllClubs();

    Club saveClub(ClubDTO club);

    ClubDTO getClubById(Long id);

    Club updateClub(ClubDTO club);

    void deleteClub(Long id);
}
