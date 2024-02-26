package com.APIclubApp.clubApp.service;

import com.APIclubApp.clubApp.model.Team;

import java.util.List;

public interface TeamService {
    List<Team> listAllTeams();

    Team saveTeam(Team team);

    Team getTeamById(Long id);

    void deleteTeam(Long id);
}
