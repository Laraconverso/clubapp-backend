package com.APIclubApp.clubApp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="games")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "game_id")
    private Long gameId;

    @Column(name = "game_day", nullable = false, unique = false)
    private String gameDay;

    @Column(name = "game_time", nullable = false, unique = false)
    private String gameTime;

    @Column(name = "game_islocal", nullable = false, unique = false)
    private Boolean gameIslocal;

    @Column(name = "game_teamrival", nullable = false, unique = false)
    private String gameTeamrival;

    @Column(name = "game_location")
    private String location;

    @Column(name = "game_localgoals", nullable = true, unique = false)
    private Integer gameLocalgoals;

    @Column(name = "game_rivalgoals", nullable = true, unique = false)
    private Integer gameRivalgoals;

    //varios equipos
    /*@ManyToMany(cascade = {CascadeType.MERGE})
    @JoinTable(name = "game_has_team", joinColumns = @JoinColumn(name = "id_game"), inverseJoinColumns = @JoinColumn(name = "id_team"))
    private List<Team> gameTeams;*/

    //solo un equipo por partido
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_fixture", nullable = true)
    @JsonIgnore
    private Fixture fixture;

}
