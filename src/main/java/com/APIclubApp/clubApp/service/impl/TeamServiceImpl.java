package com.APIclubApp.clubApp.service.impl;

import com.APIclubApp.clubApp.dto.TeamDTO;
import com.APIclubApp.clubApp.model.Category;
import com.APIclubApp.clubApp.model.Team;
import com.APIclubApp.clubApp.repository.TeamRepository;
import com.APIclubApp.clubApp.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    private TeamRepository teamRepository;

    @Override
    public List<Team> listAllTeams() {
        return teamRepository.findAll();
    }

    @Override
    public Team saveTeam(TeamDTO team) {
        Team t = new Team();
        t.setTeamId(team.getTeamId());
        t.setTeamDescription(team.getTeamDescription());
        t.setTeamName(team.getTeamName());
        return teamRepository.save(t);
    }

    @Override
    public Team getTeamById(Long id) {
        return teamRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteTeam(Long id) {
        teamRepository.deleteById(id);
    }
}
