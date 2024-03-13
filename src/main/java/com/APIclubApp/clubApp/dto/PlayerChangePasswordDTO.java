package com.APIclubApp.clubApp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class PlayerChangePasswordDTO {

    private Long playerId;
    private String userPassword;
    //private Long clubId;
}
