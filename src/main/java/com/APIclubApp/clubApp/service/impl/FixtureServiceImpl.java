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
    public FixtureDTO saveFixture(FixtureDTO fixtureDTO) {
        // Mapear FixtureDTO a Fixture
        Fixture fixture = modelMapper.map(fixtureDTO, Fixture.class);

        // Obtener los juegos asociados por sus IDs y asociarlos con la Fixture
        if (fixtureDTO.getGameIds() != null && !fixtureDTO.getGameIds().isEmpty()) {
            Set<Game> games = new HashSet<>();
            for (Long gameId : fixtureDTO.getGameIds()) {
                Game game = gameRepository.findById(gameId).orElse(null);
                if (game != null) {
                    games.add(game);
                }
            }
            fixture.setFixtureGames(games);
        }

        // Guardar la Fixture en la base de datos
        fixture = fixtureRepository.save(fixture);

        // Mapear Fixture de vuelta a FixtureDTO y devolverlo
        return modelMapper.map(fixture, FixtureDTO.class);
    }

    @Override
    public Fixture getFixtureById(Long id) {
        return fixtureRepository.findById(id).orElse(null);

        /*Fixture fixture = fixtureRepository.findById(id).orElse(null);
        return modelMapper.map(fixture, FixtureDTO.class);*/
    }

    @Override
    public FixtureDTO updateFixture(FixtureDTO fixtureDTO) {
        //return fixtureRepository.save(fixture);
        Fixture fixture = modelMapper.map(fixtureDTO, Fixture.class);
        fixture = fixtureRepository.save(fixture);
        return modelMapper.map(fixture, FixtureDTO.class);
    }

    @Override
    public void deleteFixture(Long id) {
        fixtureRepository.deleteById(id);
    }
}

