package com.APIclubApp.clubApp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name= "clubs")
public class Club {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="club_id")
    private Long clubId;

    @Column(name = "club_name", nullable = false, unique = true)
    private String clubName;

    @Column(name = "club_logo", nullable = false)
    private String clubLogo;

    @Column(name = "club_description", nullable = false)
    private String clubDescription;

    @OneToMany(mappedBy = "club", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Coach> coaches =new HashSet<Coach>();

    @OneToMany(mappedBy = "club", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Player> players =new HashSet<Player>();

    @OneToMany(mappedBy = "club", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Employee> employees =new HashSet<Employee>();


}
