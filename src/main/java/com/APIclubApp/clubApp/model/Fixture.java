package com.APIclubApp.clubApp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="fixtures")
public class Fixture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fixture_id")
    private Long fixtureId;

    @Column(name = "fixture_name")
    private String fixtureName;

    @OneToMany(mappedBy = "fixture", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Game> fixtureGames = new HashSet<Game>();

    public Fixture(String fixtureName) {
        this.fixtureName = fixtureName;
    }

    public Fixture(String fixtureName, Set<Game> fixtureGames) {
        this.fixtureName = fixtureName;
        this.fixtureGames = fixtureGames;
    }
}
