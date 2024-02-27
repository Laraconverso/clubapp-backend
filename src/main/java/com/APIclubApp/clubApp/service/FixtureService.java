package com.APIclubApp.clubApp.service;

import com.APIclubApp.clubApp.model.Fixture;

import java.util.List;

public interface FixtureService {
    List<Fixture> listAllFixtures();

    Fixture saveFixture(Fixture fixture);

    Fixture getFixtureById(Long id);

    Fixture updateFixture(Fixture fixture);

    void deleteFixture(Long id);
}

