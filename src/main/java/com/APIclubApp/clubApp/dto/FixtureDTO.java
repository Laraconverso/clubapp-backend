package com.APIclubApp.clubApp.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
