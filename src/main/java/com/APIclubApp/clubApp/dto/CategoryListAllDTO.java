package com.APIclubApp.clubApp.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CategoryListAllDTO {
    private Long categoryId;
    private String categoryName;
    private Long coachNumber;
    private List<PlayerFormDTO> players= new ArrayList<>();
}

