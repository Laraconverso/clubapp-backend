package com.APIclubApp.clubApp.service;

import com.APIclubApp.clubApp.dto.CategoryDTO;
import com.APIclubApp.clubApp.dto.CategoryListAllDTO;
import com.APIclubApp.clubApp.dto.PlayerFormDTO;
import com.APIclubApp.clubApp.model.Category;

import java.util.List;

public interface CategoryService {
   // List<CategoryDTO> listAllCategories();
   List<CategoryListAllDTO> listAllCategories();
    Category saveCategory(CategoryDTO category);

    Category getCategoryById(Long id);
    List<PlayerFormDTO> playersByCategory(Long id);

    Category updateCategory(CategoryDTO category);

    void deleteCategory(Long id);
    //agregado
    CategoryListAllDTO getCategoryByName(String categoryName);
}
