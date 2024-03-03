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
@Table(name="categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "category_id")
    private Long categoryId;

    //se llamara de acuerdo al año de nacimiento de los jugadores
    @Column(name= "category_name") // Este Id como id
    private String categoryName;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "coach_number")
//    @JsonIgnore
    private Coach coach;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "team_id")
    private Team team;

    @Column(name = "category_schedule", nullable = false, unique = false)
    private String categorySchedule;

    @Column(name = "category_daytraining", nullable = false, unique = false)
    private String categoryDaytraining;

    @Column(name = "category_fee", nullable = false, unique = false)
    private String categoryFee;

    @OneToMany(mappedBy= "category", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Player> playersTeam= new HashSet<Player>();

    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Game> gamesCategory = new HashSet<Game>();


}
