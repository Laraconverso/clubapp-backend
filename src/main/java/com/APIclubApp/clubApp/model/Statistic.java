package com.APIclubApp.clubApp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="statistics")
public class Statistic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id_statistic")
    private Long idStatistic;

    @Column(name= "goal_scored", nullable = false, unique = false)
    private Integer goalScored;

    @Column(name= "goal_assisted", nullable = false, unique = false)
    private Integer goalAssisted;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_team")
    private Team team;


}
