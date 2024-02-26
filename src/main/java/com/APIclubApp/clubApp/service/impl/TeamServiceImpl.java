package com.APIclubApp.clubApp.service.impl;

import com.APIclubApp.clubApp.model.Team;
import com.APIclubApp.clubApp.repository.TeamRepository;
import com.APIclubApp.clubApp.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    private TeamRepository teamRepository;

    @Override
    public List<Team> listAllTeams() {
        return teamRepository.findAll();
    }

    @Override
    public Team saveTeam(Team team) {
        return teamRepository.save(team);
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
