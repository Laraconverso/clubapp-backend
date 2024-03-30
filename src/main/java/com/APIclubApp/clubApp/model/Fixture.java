package com.APIclubApp.clubApp.model;

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

    @OneToMany(mappedBy = "fixture", fetch = FetchType.EAGER /*, cascade = CascadeType.ALL*/)
    //@JsonIgnore
    private Set<Game> fixtureGames = new HashSet<>();

    public Fixture(String fixtureName) {
        this.fixtureName = fixtureName;
    }

    public Fixture(String fixtureName, Set<Game> fixtureGames) {
        this.fixtureName = fixtureName;
        this.fixtureGames = fixtureGames;
    }
}
