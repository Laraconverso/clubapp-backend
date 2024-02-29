package com.APIclubApp.clubApp.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlayerFormDTO {
    private String userName;
    private String userLastname;
    private String userDni;
    private String userEmail;
    private String userAddress;
    private String playerBirthdate;

}
