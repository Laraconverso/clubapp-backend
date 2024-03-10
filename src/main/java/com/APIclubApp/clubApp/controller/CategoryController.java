package com.APIclubApp.clubApp.controller;

import com.APIclubApp.clubApp.dto.CategoryDTO;
import com.APIclubApp.clubApp.dto.CategoryListAllDTO;
import com.APIclubApp.clubApp.dto.PlayerFormDTO;
import com.APIclubApp.clubApp.model.Category;
import com.APIclubApp.clubApp.model.Employee;
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
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id){

        Category category = categoryService.getCategoryById(id);
        if (category != null) {
            return ResponseEntity.ok(category);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(summary = "Crear categoria")
    @PostMapping("/save")
    public ResponseEntity<Category> saveCategory(@RequestBody CategoryDTO category){
        return ResponseEntity.ok(categoryService.saveCategory(category));
    }



    @Operation(summary = "Actualizar categoria")
    @PutMapping("/update")
    @PermitAll
    public ResponseEntity<Category> updateCategory(@RequestBody CategoryDTO category){
        ResponseEntity<Category> response;
        if (category.getCategoryId() != null /*&& categoryService.getCategoryById(category.getCategoryId()) != null*/){
            response = ResponseEntity.ok(categoryService.updateCategory(category));
        }else{
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }

    @Operation(summary = "Borrar categoria por id")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id){
        categoryService.deleteCategory(id);
        return ResponseEntity.ok().body("Deleted");
    }

    //agregado
    @GetMapping("/search/{categoryName}")
    public ResponseEntity<CategoryListAllDTO> getCategoryByName(@PathVariable String categoryName) {
        CategoryListAllDTO categoryDTO = categoryService.getCategoryByName(categoryName);
        if (categoryDTO == null) {
            return ResponseEntity.notFound().build(); // Devolver 404 si la categoría no se encuentra
        }
        return ResponseEntity.ok(categoryDTO);
    }


}


