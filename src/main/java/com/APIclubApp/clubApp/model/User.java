package com.APIclubApp.clubApp.model;

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
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
//@Table(name="users")
public abstract class User {
  
    //@Id
    /*@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long idUser;*/
  
    @Column(name= "user_name", nullable = false, unique = false)
    private String userName;

    @Column(name = "user_lastname", nullable = false, unique = false)
    private String userLastname;

    @Column(name = "user_dni", nullable = false, unique = false)
    private String userDni;

    @Column(name = "user_email", nullable = false, unique = false)
    private String userEmail;

    @Column(name = "user_address", nullable = false, unique = false)
    private String userAddress;

    @Column(name = "user_password", nullable = false, unique = false)
    private String userPassword;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_role")
    @JsonIgnore
    private Role role;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_club")
    @JsonIgnore
    private Club club;




}
