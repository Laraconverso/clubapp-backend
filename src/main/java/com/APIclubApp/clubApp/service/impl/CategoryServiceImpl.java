package com.APIclubApp.clubApp.service.impl;

import com.APIclubApp.clubApp.dto.CategoryDTO;
import com.APIclubApp.clubApp.dto.CategoryListAllDTO;
import com.APIclubApp.clubApp.dto.CoachCategryDTO;
import com.APIclubApp.clubApp.dto.PlayerFormDTO;
import com.APIclubApp.clubApp.exception.AlreadyExistsException;
import com.APIclubApp.clubApp.exception.NotFoundException;
import com.APIclubApp.clubApp.model.Category;
import com.APIclubApp.clubApp.model.Coach;
import com.APIclubApp.clubApp.model.Player;
import com.APIclubApp.clubApp.model.Team;
import com.APIclubApp.clubApp.repository.CategoryRepository;
import com.APIclubApp.clubApp.repository.CoachRepository;
import com.APIclubApp.clubApp.repository.PlayerRepository;
import com.APIclubApp.clubApp.repository.TeamRepository;
import com.APIclubApp.clubApp.service.CategoryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Qualifier("CategoryServiceImpl")
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
    @Autowired
    private PlayerRepository playerRepository;

    @Override
    public List<CategoryListAllDTO> listAllCategories() {
        //return categoryRepository.findAll();

        List<Category> allCategories = categoryRepository.findAll();
        List<CategoryListAllDTO> allCategoriesDTO = new ArrayList<>();// preguntar a Lara pq da error con Hashset
        CategoryListAllDTO categoryListAllDTO = null;
        for (Category category : allCategories) {

            categoryListAllDTO = objectMapper.convertValue(category, CategoryListAllDTO.class);

            categoryListAllDTO.setPlayers(playersByCategory(categoryListAllDTO.getCategoryId()));
            allCategoriesDTO.add(categoryListAllDTO);
        }
        return allCategoriesDTO;
    }


    @Override
    public Category saveCategory(CategoryDTO categoryDto) {
        Category existingCategory= categoryRepository.findByCategoryName(categoryDto.getCategoryName());
        if (existingCategory !=null) {
            throw new AlreadyExistsException("Category already exists with name: " + categoryDto.getCategoryName());
        }
//        Optional<Category> existingCategoryWithSameCoach = categoryRepository.findByCoachNumber(categoryDto.getCoachNumber());
//        if (existingCategoryWithSameCoach.isPresent()) {
//            throw new AlreadyExistsException("Coach is already associated with another category.");
//        }

        // Map CategoryDTO to Category using ObjectMapper
        Category category = objectMapper.convertValue(categoryDto, Category.class);

        // Fetch the Coach object from the database by its ID
        Coach coach = coachRepository.findById(categoryDto.getCoachNumber())
                .orElseThrow(() -> new NotFoundException("Coach not found"));

        // Fetch the Team object from the database by its ID
        Team team = teamRepository.findById(categoryDto.getTeamId())
                .orElseThrow(() -> new NotFoundException("Team not found"));

        // Set the fetched Coach and Team objects in the Category object
        category.setCoach(coach);
        category.setTeam(team);
        // Save the category to the database
        return categoryRepository.save(category);
    }


    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Category not found with ID: " + id));
    }

    @Override
    public List<PlayerFormDTO> playersByCategory(Long id) {
        List<Player> allPlayerByCategory = playerRepository.findAllByCategoryId(id);
        List<PlayerFormDTO> allPlayersDTOByCategory = new ArrayList<>();
        for (Player player : allPlayerByCategory)
            allPlayersDTOByCategory.add(objectMapper.convertValue(player, PlayerFormDTO.class));

        return allPlayersDTOByCategory;


    }

    @Override
    public Category updateCategory(CategoryDTO categoryDto) {
        Category existingCategory = categoryRepository.findById(categoryDto.getCategoryId())
                .orElseThrow(() -> new NotFoundException("Category not found with ID: " + categoryDto.getCategoryId()));

        Coach coach = coachRepository.findById(categoryDto.getCoachNumber())
                .orElseThrow(() -> new NotFoundException("Coach not found with ID: " + categoryDto.getCoachNumber()));

        Team team = teamRepository.findById(categoryDto.getTeamId())
                .orElseThrow(() -> new NotFoundException("Team not found with ID: " + categoryDto.getTeamId()));


//        Optional<Category> existingCategoryWithSameCoach = categoryRepository.findByCoachNumber(categoryDto.getCoachNumber());
//        if (existingCategoryWithSameCoach.isPresent()
//                && !existingCategoryWithSameCoach.get().equals(existingCategory)) {
//            throw new AlreadyExistsException("The coach is already associated with another category.");
//        }
        Category existingCategoryWithSameName = categoryRepository.findByCategoryName(categoryDto.getCategoryName());
        if (existingCategoryWithSameName != null && !existingCategoryWithSameName.getCategoryName().equals(existingCategory.getCategoryName())) {
            throw new AlreadyExistsException("Category with name " + categoryDto.getCategoryName() + "' already exists.");
        }

        Category editCategory = objectMapper.convertValue(categoryDto, Category.class);

        editCategory.setTeam(team);
        editCategory.setCoach(coach);

        return categoryRepository.save(editCategory);
    }

    @Override
    public void deleteCategory(Long id) {
        if (categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
        } else {
            throw new NotFoundException("Category not found with ID: " + id);
        }    }
//  agregado

    @Override
    public CategoryListAllDTO getCategoryByName(String categoryName) {
        Category category = categoryRepository.findByCategoryName(categoryName);
        if (category == null) {
            throw new NotFoundException("Category not found with name: " + categoryName);
        }
        CategoryListAllDTO categoryDTO = objectMapper.convertValue(category, CategoryListAllDTO.class);
        categoryDTO.setPlayers(playersByCategory(categoryDTO.getCategoryId()));

        return categoryDTO;
    }

    @Override
    public Coach updateCategoryCoach(String coachDni, String categoryName) {
        Category category = categoryRepository.findByCategoryName(categoryName);
        Coach coach = coachRepository.findByUserDni(coachDni);
        if (category != null && coach!=null) {
            category.setCoach(coach);
            categoryRepository.save(category);
            return coach;
        } else {
            throw new NotFoundException("Category not found with Name: " + categoryName);
        }
    }
}


