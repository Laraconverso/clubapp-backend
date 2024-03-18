package com.APIclubApp.clubApp.service.impl;

import com.APIclubApp.clubApp.dto.TeamDTO;
import com.APIclubApp.clubApp.exception.AlreadyExistsException;
import com.APIclubApp.clubApp.exception.AssociatedCategoriesException;
import com.APIclubApp.clubApp.exception.NotFoundException;
import com.APIclubApp.clubApp.model.Team;
import com.APIclubApp.clubApp.repository.TeamRepository;
import com.APIclubApp.clubApp.service.TeamService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    public ModelMapper modelMapper;

    @Override
    public List<Team> listAllTeams() {
        return teamRepository.findAll();
    }

    @Override
    public Team saveTeam(TeamDTO team) {
        Optional<Team> existingTeam = teamRepository.findByTeamName(team.getTeamName());
        if (existingTeam.isPresent()) {
            throw new AlreadyExistsException("Team already exists with name: " + team.getTeamName());
        }else{

            Team  newTeam = modelMapper.map(team, Team.class);

            return teamRepository.save(newTeam);}
    }

    @Override
    public Team getTeamById(Long id) {
        return teamRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Team not found with ID: " + id));
    }
    @Override
    public Team updateTeamById(TeamDTO team) {
        Team existingTeam = teamRepository.findById(team.getTeamId())
                .orElseThrow(() -> new NotFoundException("Team not found with ID: " + team.getTeamId()));

        Optional<Team> existingTeamWithSameName = teamRepository.findByTeamName(team.getTeamName());
        if (existingTeamWithSameName.isPresent() && !existingTeamWithSameName.get().getTeamId().equals(existingTeam.getTeamId())) {
            throw new AlreadyExistsException("Team already exists with name: " + team.getTeamName());
        }

        Team  updatedTeam = modelMapper.map(team, Team.class);

        return teamRepository.save(updatedTeam);
    }
    @Override
    public void deleteTeam(Long id) {
        Team team = teamRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Team not found with ID: " + id));

        if (!team.getCategoriesTeam().isEmpty()) {
            throw new AssociatedCategoriesException("Cannot delete team with associated categories");
        }

        teamRepository.deleteById(id);
    }
}
