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

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
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

    @Override
    public Fixture getFixtureByIdWithGames(Long id) {
        return fixtureRepository.findByIdWithGames(id);
    }


    @Override
    public Fixture saveFixture(FixtureDTO fixtureDTO) {

        Fixture fixture = modelMapper.map(fixtureDTO, Fixture.class);

        // Obtener los IDs de los juegos que se deben agregar al fixture
        Set<Long> gameIds = fixtureDTO.getGameIds();

        // Obtener los juegos correspondientes a los IDs
        List<Game> gamesToAdd = gameRepository.findAllById(gameIds);

        // Establecer la relación en ambos sentidos
        for (Game game : gamesToAdd) {
            fixture.getFixtureGames().add(game); // Agregar el juego al conjunto de juegos del fixture
            game.setFixture(fixture); // Establecer el fixture en el juego
        }

        // Guardar el fixture (y por ende, también los juegos)
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

