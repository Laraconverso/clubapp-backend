package com.APIclubApp.clubApp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name="fixtures")
public class Fixture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_fixture")
    private Long idFixture;

    @Column(name = "name_fixture")
    private String nameFixture;

    @OneToMany(mappedBy = "fixture", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Game> gamesFixture = new HashSet<Game>();

    public Fixture() {
    }

    public Fixture(Long idFixture, String nameFixture, Set<Game> gamesFixture) {
        this.idFixture = idFixture;
        this.nameFixture = nameFixture;
        this.gamesFixture = gamesFixture;
    }

    public Fixture(String nameFixture, Set<Game> gamesFixture) {
        this.nameFixture = nameFixture;
        this.gamesFixture = gamesFixture;
    }


}
