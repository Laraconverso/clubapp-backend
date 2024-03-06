package com.APIclubApp.clubApp.service.impl;

import com.APIclubApp.clubApp.dto.EmployeeDTO;
import com.APIclubApp.clubApp.dto.FixtureDTO;
import com.APIclubApp.clubApp.model.Employee;
import com.APIclubApp.clubApp.model.Fixture;
import com.APIclubApp.clubApp.model.Game;
import com.APIclubApp.clubApp.repository.FixtureRepository;
import com.APIclubApp.clubApp.repository.GameRepository;
import com.APIclubApp.clubApp.service.FixtureService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class FixtureServiceImpl implements FixtureService {
    @Autowired
    private FixtureRepository fixtureRepository;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    //ObjectMapper objectMapper;
    public ModelMapper modelMapper; // Necesitamos una instancia de ModelMapper para convertir entre Employee y EmployeeDTO

    @Override
    public List<Fixture> listAllFixtures() {
        return fixtureRepository.findAll();

        /*List<Fixture> fixtures = fixtureRepository.findAll();
        return fixtures.stream()
                .map(fixture -> modelMapper.map(fixture, FixtureDTO.class))
                .collect(Collectors.toList());*/
    }

    /*@Override
    public Fixture getFixtureByIdWithGames(Long id) {
        return fixtureRepository.findByIdWithGames(id);
    }*/

    @Override
    public List<Object[]> listAllFixtureIdAndName() {
        return fixtureRepository.findAllFixtureIdAndName();
    }


    @Override
    public Fixture saveFixture(FixtureDTO fixtureDTO) {
        Fixture fixture = modelMapper.map(fixtureDTO, Fixture.class);

        Set<Long> gameIds = fixtureDTO.getGameIds();

        List<Long> gameIdList = new ArrayList<>(gameIds);

        List<Game> gamesToAdd = gameRepository.findAllById(gameIdList);

        fixture.setFixtureGames(new HashSet<>(gamesToAdd));

        return fixtureRepository.save(fixture);
    }



    @Override
    public Fixture getFixtureById(Long id) {
        return fixtureRepository.findById(id).orElse(null);

        /*Fixture fixture = fixtureRepository.findById(id).orElse(null);
        return modelMapper.map(fixture, FixtureDTO.class);*/
    }

    @Override
    public Fixture updateFixture(FixtureDTO fixtureDTO) {

        Fixture fixture = modelMapper.map(fixtureDTO, Fixture.class);
        return fixtureRepository.save(fixture);
    }

    @Override
    public void deleteFixture(Long id) {
        fixtureRepository.deleteById(id);
    }
}

