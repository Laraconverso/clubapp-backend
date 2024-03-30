package com.APIclubApp.clubApp.dto;


import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CoachDTO {

    private Long coachNumber;
    private String userName;
    private String userLastname;
    private String userDni;
    private String userEmail;
    private String userAddress;
    private String userPassword;
    private Long roleId;
    //private RolDTO rol;
    private Long clubId;
    //private ClubDTO club;
    //private Long categoryId;
    //private CategoryDTO category;

}
