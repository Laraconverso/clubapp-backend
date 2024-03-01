package com.APIclubApp.clubApp.service.impl;

import com.APIclubApp.clubApp.dto.CategoryDTO;
import com.APIclubApp.clubApp.model.Category;
import com.APIclubApp.clubApp.model.Coach;
import com.APIclubApp.clubApp.model.Team;
import com.APIclubApp.clubApp.repository.CategoryRepository;
import com.APIclubApp.clubApp.repository.CoachRepository;
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
    private CoachRepository coachRepository;
    @Autowired
    private TeamRepository teamRepository;

    @Override
    public List<Category> listAllCategories() {
        return categoryRepository.findAll();}
//        List<Category> allCategories = categoryRepository.findAll();
//        List<CategoryDTO> allCategoriesDTO = new ArrayList<>();// preguntar a Lara pq da error con Hashset
//        for(Category category: allCategories)
//            allCategoriesDTO.add(objectMapper.convertValue(category,CategoryDTO.class));
//
//        return allCategoriesDTO;
//        }




//    @Override
//    public Category saveCategory(CategoryDTO category) {
//        Category newCategory=  objectMapper.convertValue(category, Category.class);
//        Coach coach= coachRepository.getReferenceById(category.getCoachNumber());
//        Team team= teamRepository.getReferenceById(category.getTeamId());
//        newCategory.setCoach(coach);
//        newCategory.setTeam(team);
//        return categoryRepository.save(newCategory);
//    }
    @Override
    public Category saveCategory(CategoryDTO categoryDto) {
        // Map CategoryDTO to Category using ModelMapper
        Category category = objectMapper.convertValue(categoryDto, Category.class);

        // Fetch the Coach object from the database by its ID
        Coach coach = coachRepository.findById(categoryDto.getCoachNumber())
                .orElseThrow(() -> new RuntimeException("Coach not found"));

        // Fetch the Team object from the database by its ID
        Team team = teamRepository.findById(categoryDto.getTeamId())
                .orElseThrow(() -> new RuntimeException("Team not found"));

        // Set the fetched Coach and Team objects in the Category object
        category.setCoach(coach);
        category.setTeam(team);

        // Save the Category object
        return categoryRepository.save(category);
    }

    @Override
    public CategoryDTO getCategoryById(Long id) {

        //return categoryRepository.findById(id).get();
        Optional<Category> categoriaBuscada=categoryRepository.findById(id);
        return objectMapper.convertValue(categoriaBuscada,CategoryDTO.class);
    }

    @Override
    public Category updateCategory(CategoryDTO category) {

        Category editCategory=  objectMapper.convertValue(category, Category.class);
        return categoryRepository.save(editCategory);
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
