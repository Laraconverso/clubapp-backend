package com.APIclubApp.clubApp.dto;

import com.APIclubApp.clubApp.model.Coach;
import com.APIclubApp.clubApp.model.Employee;
import com.APIclubApp.clubApp.model.Player;
import lombok.*;

import java.util.HashSet;
import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ClubDTO {
    private Long clubId;
    private String clubName;
    private String clubLogo;
    private String clubDescription;
    private Set<Coach> coaches =new HashSet<Coach>();//Seria DTO hay q cambiar cdo lo haga
    private Set<Player> players =new HashSet<Player>(); //Idem anterior
    private Set<Employee> employees =new HashSet<Employee>(); //Idem 2 anteriores
}
