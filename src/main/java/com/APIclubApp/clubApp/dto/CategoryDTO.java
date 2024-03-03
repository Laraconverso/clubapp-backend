package com.APIclubApp.clubApp.dto;

import com.APIclubApp.clubApp.model.Coach;
import com.APIclubApp.clubApp.model.Game;
import com.APIclubApp.clubApp.model.Player;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CategoryDTO {

    private Long categoryId;
    private String categoryName;
    private Long coachNumber;
    private Long teamId;
    private String categorySchedule;
    private String categoryDaytraining;
    private String categoryFee;
    //private Set<PlayerDTO> playersDtoTeam= new HashSet<PlayerDTO>(); VER CON LARA
    //private Set<GameDTO> gamesDtoCategory = new HashSet<GameDTO>(); VER CON LARA*/

}
