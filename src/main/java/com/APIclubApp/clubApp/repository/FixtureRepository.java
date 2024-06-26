package com.APIclubApp.clubApp.repository;

import com.APIclubApp.clubApp.model.Fixture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FixtureRepository extends JpaRepository<Fixture,Long> {
    /*@Query("SELECT f FROM Fixture f LEFT JOIN FETCH f.fixtureGames WHERE f.id = :id")
    Fixture findByIdWithGames(@Param("id") Long id);*/

    @Query("SELECT f.fixtureId, f.fixtureName FROM Fixture f")
    List<Object[]> findAllFixtureIdAndName();

}
