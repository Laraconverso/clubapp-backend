package com.APIclubApp.clubApp.service;

import com.APIclubApp.clubApp.dto.CategoryDTO;
import com.APIclubApp.clubApp.model.Category;

import java.util.List;

public interface CategoryService {
   // List<CategoryDTO> listAllCategories();
   List<Category> listAllCategories();
    Category saveCategory(CategoryDTO category);

    CategoryDTO getCategoryById(Long id);

    Category updateCategory(CategoryDTO category);

    void deleteCategory(Long id);
}
