package com.APIclubApp.clubApp.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name="players")
public class Player extends User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_player")
    private Long idPlayer;

//    @Column(name = "player_position", nullable = true, unique = false)
//    @JsonIgnore
//    private Long playerPosition;

//    @Column(name = "player_image", nullable = false, unique = false)
//    @JsonIgnore
//    private String playerImage;

    @Column(name = "player_birthdate", nullable = false, unique = false)
    private String playerBirthdate;

//    @Column(name = "player_feePaid", nullable = false, unique = false)
//    @JsonIgnore
//    private Boolean playerFeePaid;


    /*@Column(name = "member_ChangePassword", nullable = false, unique = false)
    private Boolean memberChangePassword;*/

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_team")
    @JsonIgnore
    private Team team;


}
