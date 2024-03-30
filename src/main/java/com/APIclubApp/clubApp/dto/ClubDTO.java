package com.APIclubApp.clubApp.dto;


import lombok.*;




@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ClubDTO {
    private Long clubId;
    private String clubName;
    private String clubLogo;
    private String clubDescription;
    //private Set<Coach> coaches =new HashSet<Coach>();//Seria DTO hay q cambiar cdo lo haga
    //private Set<Player> players =new HashSet<Player>(); //Idem anterior
    //private Set<Employee> employees =new HashSet<Employee>(); //Idem 2 anteriores
}
