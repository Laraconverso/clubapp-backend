package com.APIclubApp.clubApp.dto;

import com.APIclubApp.clubApp.model.Game;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
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
public class FixtureDTO {
    private Long fixtureId;
    private String fixtureName;
    private Set<Long> gameIds;
}
