package com.APIclubApp.clubApp.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class PlayerDTO {
    private Long playerId;
    private String playerPosition;
    private String playerBirthdate;
    private Boolean playerFeePaid;
    private Boolean playerPasswordChanged;
    private Long categoryId;
    private String userName;
    private String userLastname;
    private String userDni;
    private String userEmail;
    private String userAddress;
    private String userPassword;
//    private Long roleId;
    private Long clubId;
}