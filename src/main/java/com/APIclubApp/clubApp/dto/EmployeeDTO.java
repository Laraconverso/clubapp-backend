package com.APIclubApp.clubApp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class EmployeeDTO {

        private Long employeeNumber;
        private String userName;
        private String userLastname;
        private String userDni;
        private String userEmail;
        private String userAddress;
        private String userPassword;
        private Long roleId;
        //private String roleName;
        private Long clubId;
       // private Long clubId;
        //private String clubName;
        //private String clubLogo;
        //private String clubDescription;

}
