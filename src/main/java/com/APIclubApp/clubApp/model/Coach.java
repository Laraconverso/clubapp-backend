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
@Table(name="coaches")
public class Coach extends User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="coach_number")
    private Long coachNumber;

    /*@Column(name="coach_dni")
    private Long coachDni;*/

    @OneToMany(mappedBy = "coach", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Team> assignedTeams =new HashSet<Team>();


}
