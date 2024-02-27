package com.APIclubApp.clubApp.service;

import com.APIclubApp.clubApp.model.Category;

import java.util.List;

public interface CategoryService {
    public List<Category> listAllCategories();

    Category saveCategory(Category category);

    Category getCategoryById(Long id);

    Category updateCategory(Category category);

    void deleteCategory(Long id);
}
