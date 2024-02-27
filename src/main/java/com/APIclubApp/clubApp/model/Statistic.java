package com.APIclubApp.clubApp.model;

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
@Table(name="statistics")
public class Statistic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "statistic_id")
    private Long statisticId;

    @Column(name= "goal_scored", nullable = false, unique = false)
    private Integer goalScored;

    @Column(name= "goal_assisted", nullable = false, unique = false)
    private Integer goalAssisted;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "team_id")
    private Team team;

    public void setId(Long id) {
    }
}
