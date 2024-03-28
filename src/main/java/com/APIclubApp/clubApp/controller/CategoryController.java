package com.APIclubApp.clubApp.controller;

import com.APIclubApp.clubApp.dto.CategoryDTO;
import com.APIclubApp.clubApp.dto.CategoryListAllDTO;
import com.APIclubApp.clubApp.dto.PlayerFormDTO;
import com.APIclubApp.clubApp.exception.AlreadyExistsException;
import com.APIclubApp.clubApp.exception.NotFoundException;
import com.APIclubApp.clubApp.model.Category;
import com.APIclubApp.clubApp.model.Coach;
import com.APIclubApp.clubApp.model.Employee;
import com.APIclubApp.clubApp.model.Player;
import com.APIclubApp.clubApp.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Operation(summary = "Listar categorias")
    @GetMapping("/list")
    public ResponseEntity<List<CategoryListAllDTO>> listAllCategories(){
        return ResponseEntity.ok(categoryService.listAllCategories());
    }
    @Operation(summary = "Obtener una categoria por su ID")
    @GetMapping("/get/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable Long id){
        try {
            Category category = categoryService.getCategoryById(id);
            return ResponseEntity.ok(category);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @Operation(summary = "Crear categoria")
    @PostMapping("/save")
    public ResponseEntity<?> saveCategory(@RequestBody CategoryDTO category){
        try {
            return ResponseEntity.ok(categoryService.saveCategory(category));
        }
        catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }



    @Operation(summary = "Actualizar categoria")
    @PutMapping("/update")
    @PermitAll
    public ResponseEntity<?> updateCategory(@RequestBody CategoryDTO category){
        try {
            return ResponseEntity.ok(categoryService.updateCategory(category));
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
//        catch (AlreadyExistsException e) {
//            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
//        }
    }

    @Operation(summary = "Borrar categoria por id")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id){
        try {
            categoryService.deleteCategory(id);
            return ResponseEntity.ok().body("Category deleted successfully");
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    //agregado
    @GetMapping("/search/{categoryName}")
    public ResponseEntity<?> getCategoryByName(@PathVariable String categoryName) {
        try {
            CategoryListAllDTO categoryDTO = categoryService.getCategoryByName(categoryName);
            return ResponseEntity.ok(categoryDTO);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


}


