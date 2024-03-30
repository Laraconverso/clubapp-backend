package com.APIclubApp.clubApp.security.usersecurity.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name= "user_email", nullable = true, unique = false)
    private String email;

    @Column(name= "user_name", nullable = true, unique = false)
    @Size(max = 35)
    private String username;

    @Column(name= "user_dni", nullable = true, unique = false)
    @Size(max = 12)
    private String userDni;

    @Column(name= "user_password", nullable = true, unique = false)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, targetEntity = RoleEntity.class, cascade = CascadeType.PERSIST)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<RoleEntity> roles;
}