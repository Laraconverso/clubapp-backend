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
@Table(name="team")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id")
    private Long teamId;

    @Column(name = "team_name", nullable = false, unique = false)
    private String teamName;

    @Column(name = "team_description", nullable = false, unique = false)
    private String teamDescription;

    @OneToMany(mappedBy = "team", fetch = FetchType.EAGER)
    @JsonIgnore
    //@JoinColumn(name="category_id")
    private Set<Category> categoriesTeam = new HashSet<Category>();
}

/*
    @ManyToOne(fetch = FetchType.EAGER)
    @PrimaryKeyJoinColumn(name = "coach_number")
    // @JoinColumn(name="coach_number")
    private Coach coach;

    @Column(name = "team_schedule", nullable = false, unique = false)
    private String teamSchedule;

    @Column(name = "team_daytraining", nullable = false, unique = false)
    private String teamDaytraining;

    @Column(name = "team_fee", nullable = false, unique = false)
    private String teamFee;



    @OneToMany(mappedBy = "team", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Game> gamesTeam = new HashSet<Game>();

    @OneToOne(mappedBy = "team", fetch = FetchType.EAGER)
    @JsonIgnore
    private Statistic statistic;


 */

    /*public void setId(Long id) {
    }*/



