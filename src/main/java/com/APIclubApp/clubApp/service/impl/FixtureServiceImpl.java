package com.APIclubApp.clubApp.service.impl;

import com.APIclubApp.clubApp.model.Fixture;
import com.APIclubApp.clubApp.repository.FixtureRepository;
import com.APIclubApp.clubApp.service.FixtureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FixtureServiceImpl implements FixtureService {
    @Autowired
    private FixtureRepository fixtureRepository;

    @Override
    public List<Fixture> listAllFixtures() {
        return fixtureRepository.findAll();
    }

    @Override
    public Fixture saveFixture(Fixture fixture) {
        return fixtureRepository.save(fixture);
    }

    @Override
    public Fixture getFixtureById(Long id) {
        return fixtureRepository.findById(id).orElse(null);
    }

    @Override
    public Fixture updateFixture(Fixture fixture) {
        return fixtureRepository.save(fixture);
    }

    @Override
    public void deleteFixture(Long id) {
        fixtureRepository.deleteById(id);
    }
}

