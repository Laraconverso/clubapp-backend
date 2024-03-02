package com.APIclubApp.clubApp.service;

import com.APIclubApp.clubApp.dto.EmployeeDTO;
import com.APIclubApp.clubApp.dto.FixtureDTO;
import com.APIclubApp.clubApp.model.Fixture;

import java.util.List;

public interface FixtureService {
    List<Fixture> listAllFixtures();

    //FixtureDTO saveFixture(FixtureDTO fixtureDTO);
    Fixture saveFixture(FixtureDTO fixtureDTO);

    Fixture getFixtureById(Long id);

    Fixture updateFixture(FixtureDTO fixtureDTO);

    //Fixture getFixtureByIdWithGames(Long id);

    List<Object[]> listAllFixtureIdAndName();

    void deleteFixture(Long id);

}

