package com.APIclubApp.clubApp.dto;

import com.APIclubApp.clubApp.model.Category;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TeamDTO {
    private Long teamId;
    private String teamName;
    private String teamDescription;
//    private Set<Category> categoriesTeam; //consultar como es el form de carga de equipos.
}
