package com.APIclubApp.clubApp.repository;

import com.APIclubApp.clubApp.model.Fixture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface FixtureRepository extends JpaRepository<Fixture,Long> {
}
