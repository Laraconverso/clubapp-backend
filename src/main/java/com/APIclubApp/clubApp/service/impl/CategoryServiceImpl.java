package com.APIclubApp.clubApp.service.impl;

import com.APIclubApp.clubApp.dto.CategoryDTO;
import com.APIclubApp.clubApp.dto.CategoryListAllDTO;
import com.APIclubApp.clubApp.dto.PlayerFormDTO;
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
            for(Category category: allCategories) {

                categoryListAllDTO = objectMapper.convertValue(category, CategoryListAllDTO.class);

                categoryListAllDTO.setPlayers(playersByCategory(categoryListAllDTO.getCategoryId()));
                allCategoriesDTO.add(categoryListAllDTO);
            }
              return allCategoriesDTO;
        }



    @Override
    public Category saveCategory(CategoryDTO categoryDto) {
        // Map CategoryDTO to Category using ObjectMapper
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
        return category;
    }


    @Override
    public Category getCategoryById(Long id) {

        return categoryRepository.findById(id).get();

    }

    @Override
    public List<PlayerFormDTO> playersByCategory(Long id) {
        List<Player> allPlayerByCategory = playerRepository.findAllByCategoryId(id);
        List<PlayerFormDTO> allPlayersDTOByCategory = new ArrayList<>();
        for(Player player: allPlayerByCategory)
            allPlayersDTOByCategory.add(objectMapper.convertValue(player,PlayerFormDTO.class));

        return allPlayersDTOByCategory;


    }

    @Override
    public Category updateCategory(CategoryDTO categoryDto) {

        Category editCategory=  objectMapper.convertValue(categoryDto, Category.class);
        // Fetch the Coach object from the database by its ID
        Coach coach = coachRepository.findById(categoryDto.getCoachNumber())
                .orElseThrow(() -> new RuntimeException("Coach not found"));

        // Fetch the Team object from the database by its ID
        Team team = teamRepository.findById(categoryDto.getTeamId())
                .orElseThrow(() -> new RuntimeException("Team not found"));

        // Set the fetched Coach and Team objects in the Category object
        editCategory.setCoach(coach);
        editCategory.setTeam(team);
        return categoryRepository.save(editCategory);
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}


