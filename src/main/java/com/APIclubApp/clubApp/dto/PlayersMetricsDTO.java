package com.APIclubApp.clubApp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlayersMetricsDTO {
    private Long totalCount;
    private int upToDateCount;
    private Long inDebtCount;
    private List<Object> playersByCategory;
}
