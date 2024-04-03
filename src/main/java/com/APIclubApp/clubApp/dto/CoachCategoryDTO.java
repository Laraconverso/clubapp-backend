package com.APIclubApp.clubApp.dto;

import com.APIclubApp.clubApp.model.Coach;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CoachCategoryDTO {
    private Coach coach;
    private Long categoryId;
}
