package com.APIclubApp.clubApp.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

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
    @Column(name = "player_id")
    private Long playerId;

    @Column(name = "player_position", nullable = true, unique = false)
    private String playerPosition;

    @Column(name = "player_birthdate", nullable = false, unique = false)
    private String playerBirthdate;

    @Column(name = "player_feePaid", nullable = true, unique = false)
    private Boolean playerFeePaid;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "player_passwordChanged")
    private Boolean playerPasswordChanged;

}
