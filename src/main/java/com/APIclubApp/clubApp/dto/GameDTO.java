package com.APIclubApp.clubApp.dto;

import com.APIclubApp.clubApp.model.Category;
import com.APIclubApp.clubApp.model.Fixture;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
    private Integer gameLocalgoals;
    private Integer gameRivalgoals;
    private Long categoryId;
    private Long fixtureId;

}
