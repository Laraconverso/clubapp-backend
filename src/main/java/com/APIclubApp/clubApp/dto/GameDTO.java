package com.APIclubApp.clubApp.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GameDTO {

    private Long gameId;
    private String gameDay;
    private String gameTime;
    private Boolean gameIslocal;
    private String gameTeamrival;
    private String location;
    private Integer gameLocalgoals;
    private Integer gameRivalgoals;
    private Long categoryId;
    private Long fixtureId;

}
