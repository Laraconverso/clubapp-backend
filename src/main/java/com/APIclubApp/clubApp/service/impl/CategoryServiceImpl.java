package com.APIclubApp.clubApp.service.impl;

import com.APIclubApp.clubApp.dto.CategoryDTO;
import com.APIclubApp.clubApp.model.Category;
import com.APIclubApp.clubApp.model.Role;
import com.APIclubApp.clubApp.model.Team;
import com.APIclubApp.clubApp.repository.CategoryRepository;
import com.APIclubApp.clubApp.repository.TeamRepository;
import com.APIclubApp.clubApp.service.CategoryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private TeamRepository teamRepository;

    @Override
    public List<Category> listAllCategories() {
        return categoryRepository.findAll();
    }
//        List<Category> allCategories = categoryRepository.findAll();
//        List<CategoryDTO> allCategoriesDTO = new ArrayList<>();// preguntar a Lara pq da error con Hashset
//        for(Category category: allCategories)
//            allCategoriesDTO.add(objectMapper.convertValue(category,CategoryDTO.class));
//
//        return allCategoriesDTO;
//        }



    @Override
    public Category saveCategory(CategoryDTO categoryDto) {
        // Map CategoryDTO to Category using ObjectMapper
        Category category = objectMapper.convertValue(categoryDto, Category.class);

        // Fetch the Team object from the database by its ID
        Team team = teamRepository.findById(categoryDto.getTeamId())
                .orElseThrow(() -> new RuntimeException("Team not found"));
        Category category1 = new Category();

        // Set the fetched  Team objects in the Category object
        category.setCategoryId(categoryDto.getCategoryId());
        category.setCategoryName(category.getCategoryName());
        category.setTeam(team);
        return categoryRepository.save(category);
    }


    @Override
    public Category getCategoryById(Long id) {

        return categoryRepository.findById(id).get();

    }

    @Override
    public Category updateCategory(CategoryDTO categoryDto) {

        Category editCategory=  objectMapper.convertValue(categoryDto, Category.class);

        // Fetch the Team object from the database by its ID
        Team team = teamRepository.findById(categoryDto.getTeamId())
                .orElseThrow(() -> new RuntimeException("Team not found"));

        // Set the fetched  Team objects in the Category object
        editCategory.setTeam(team);
        return categoryRepository.save(editCategory);
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
