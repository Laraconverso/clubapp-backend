package com.APIclubApp.clubApp.model;

import com.APIclubApp.clubApp.security.usersecurity.model.RoleEntity;
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
@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class User {

    //@Id
    /*@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long idUser;*/
  
    @Column(name= "user_name", nullable = true, unique = false)
    private String userName;

    @Column(name = "user_lastname", nullable = true, unique = false)
    private String userLastname;

    @Column(name = "user_dni", nullable = true, unique = true)
    private String userDni;

    @Column(name = "user_email", nullable = true, unique = false)
    private String userEmail;

    @Column(name = "user_address", nullable = true, unique = false)
    private String userAddress;

    @Column(name = "user_password", nullable = true, unique = false)
    private String userPassword;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    //@JsonIgnore
    private RoleEntity role;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="club_id")
    //@JsonIgnore
    private Club club;



}
