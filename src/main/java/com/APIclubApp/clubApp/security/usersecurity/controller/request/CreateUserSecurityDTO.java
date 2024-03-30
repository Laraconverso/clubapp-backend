package com.APIclubApp.clubApp.security.usersecurity.controller.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserSecurityDTO {
    @NotBlank
    private String email;
    @NotBlank
    private String username;
    @NotBlank
    private String userDNI;
    @NotBlank
    private String password;
    private Set<String> roles;

}