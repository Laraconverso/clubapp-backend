package com.APIclubApp.clubApp.service;

import com.APIclubApp.clubApp.dto.TeamDTO;
import com.APIclubApp.clubApp.model.Team;

import java.util.List;

public interface TeamService {
    List<Team> listAllTeams();

    Team saveTeam(TeamDTO team);

    Team getTeamById(Long id);
    Team updateTeamById(TeamDTO teamDTO);

    void deleteTeam(Long id);
}
