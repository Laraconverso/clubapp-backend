package com.APIclubApp.clubApp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlayersMetricsDTO {
    private Long totalCount;
    private int upToDateCount;
    private Long inDebtCount;
}
