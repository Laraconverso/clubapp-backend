package com.APIclubApp.clubApp.model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name="roles")
public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_role")
    private Long idRole;

    @Column(name="role_name", nullable = false, unique = true)
    private String roleName;

    public Role() {
    }

    public Role(Long idRole, String roleName) {
        this.idRole = idRole;
        this.roleName = roleName;
    }

    public Long getIdRole() {
        return idRole;
    }

    public void setIdRole(Long idRole) {
        this.idRole = idRole;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
