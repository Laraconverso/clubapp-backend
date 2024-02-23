package com.APIclubApp.clubApp.service;

import com.APIclubApp.clubApp.model.Club;

import java.util.List;

public interface ClubService {
    public List<Club> listAllClubes();

    public  Club saveClub(Club club);

    public Club getClubById(Long id);

    public  Club updateClub(Club club);

    public void deleteClub(Long id);
}
