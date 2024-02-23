package com.APIclubApp.clubApp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.List;
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
    @Column(name= "id_team")
    private Long idTeam;

    @ManyToOne(fetch = FetchType.EAGER)
    @PrimaryKeyJoinColumn(name = "coach_number")
   // @JoinColumn(name="coach_number")
    private Coach coach;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_category")
    private Category category;

    @Column(name = "team_name", nullable = false, unique = false)
    private String teamName;

    @Column(name = "team_schedule", nullable = false, unique = false)
    private String teamSchedule;

    @Column(name = "team_daytraining", nullable = false, unique = false)
    private String teamDaytraining;

    @Column(name = "team_fee", nullable = false, unique = false)
    private String teamFee;

    @OneToMany(mappedBy = "team", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Player> playersTeam = new HashSet<Player>();

    @OneToMany(mappedBy = "team", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Game> gamesTeam = new HashSet<Game>();

    @OneToOne(mappedBy = "team", fetch = FetchType.EAGER)
    @JsonIgnore
    private Statistic statistic;


}
