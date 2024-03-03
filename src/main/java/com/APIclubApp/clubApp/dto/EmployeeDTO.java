package com.APIclubApp.clubApp.dto;

import com.APIclubApp.clubApp.model.Club;
import com.APIclubApp.clubApp.model.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
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
