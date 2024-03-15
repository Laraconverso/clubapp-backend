package com.APIclubApp.clubApp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlayerUpdateAdminDTO {
    //nombre, apellido, email, direccion
    private Long playerId;
    private String userName;
    private String userLastname;
    private String userEmail;
    private String userAddress;

}
