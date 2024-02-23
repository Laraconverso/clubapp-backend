package com.APIclubApp.clubApp.model;

import jakarta.persistence.*;

@Entity
@Table(name="games")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id_game")
    private Long idGame;

    @Column(name = "game_day", nullable = false, unique = false)
    private String gameDay;

    @Column(name = "game_time", nullable = false, unique = false)
    private String gameTime;

    @Column(name = "game_islocal", nullable = false, unique = false)
    private Boolean gameIslocal;

    @Column(name = "game_teamrival", nullable = false, unique = false)
    private String gameTeamrival;

    @Column(name = "game_localgoals", nullable = true, unique = false)
    private Integer gameLocalgoals;

    @Column(name = "game_rivalgoals", nullable = true, unique = false)
    private Integer gameRivalgoals;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_category")
    private Category category;

    //varios equipos
    /*@ManyToMany(cascade = {CascadeType.MERGE})
    @JoinTable(name = "game_has_team", joinColumns = @JoinColumn(name = "id_game"), inverseJoinColumns = @JoinColumn(name = "id_team"))
    private List<Team> gameTeams;*/

    //solo un equipo por partido
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_team")
    private Team team;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_fixture")
    private Fixture fixture;

    public Game() {
    }

    public Game(Long idGame, String gameDay, String gameTime, Boolean gameIslocal, String gameTeamrival, Integer gameLocalgoals, Integer gameRivalgoals, Category category, Team team, Fixture fixture) {
        this.idGame = idGame;
        this.gameDay = gameDay;
        this.gameTime = gameTime;
        this.gameIslocal = gameIslocal;
        this.gameTeamrival = gameTeamrival;
        this.gameLocalgoals = gameLocalgoals;
        this.gameRivalgoals = gameRivalgoals;
        this.category = category;
        this.team = team;
        this.fixture = fixture;
    }

    public Game(String gameDay, String gameTime, Boolean gameIslocal, String gameTeamrival, Integer gameLocalgoals, Integer gameRivalgoals, Category category, Team team, Fixture fixture) {
        this.gameDay = gameDay;
        this.gameTime = gameTime;
        this.gameIslocal = gameIslocal;
        this.gameTeamrival = gameTeamrival;
        this.gameLocalgoals = gameLocalgoals;
        this.gameRivalgoals = gameRivalgoals;
        this.category = category;
        this.team = team;
        this.fixture = fixture;
    }

    public Game(Long idGame, String gameDay, String gameTime, Boolean gameIslocal, String gameTeamrival, Category category, Team team, Fixture fixture) {
        this.idGame = idGame;
        this.gameDay = gameDay;
        this.gameTime = gameTime;
        this.gameIslocal = gameIslocal;
        this.gameTeamrival = gameTeamrival;
        this.category = category;
        this.team = team;
        this.fixture = fixture;
    }

    public Long getIdGame() {
        return idGame;
    }

    public void setIdGame(Long idGame) {
        this.idGame = idGame;
    }

    public String getGameDay() {
        return gameDay;
    }

    public void setGameDay(String gameDay) {
        this.gameDay = gameDay;
    }

    public String getGameTime() {
        return gameTime;
    }

    public void setGameTime(String gameTime) {
        this.gameTime = gameTime;
    }

    public Boolean getGameIslocal() {
        return gameIslocal;
    }

    public void setGameIslocal(Boolean gameIslocal) {
        this.gameIslocal = gameIslocal;
    }

    public String getGameTeamrival() {
        return gameTeamrival;
    }

    public void setGameTeamrival(String gameTeamrival) {
        this.gameTeamrival = gameTeamrival;
    }

    public Integer getGameLocalgoals() {
        return gameLocalgoals;
    }

    public void setGameLocalgoals(Integer gameLocalgoals) {
        this.gameLocalgoals = gameLocalgoals;
    }

    public Integer getGameRivalgoals() {
        return gameRivalgoals;
    }

    public void setGameRivalgoals(Integer gameRivalgoals) {
        this.gameRivalgoals = gameRivalgoals;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Fixture getFixture() {
        return fixture;
    }

    public void setFixture(Fixture fixture) {
        this.fixture = fixture;
    }
}
