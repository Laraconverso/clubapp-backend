package com.APIclubApp.clubApp.dto;


import lombok.*;



@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CategoryDTO {

    private Long categoryId;
    private String categoryName;
    private Long coachNumber;
    private Long teamId;
    private String categorySchedule;
    private String categoryDaytraining;
    private String categoryFee;



}
