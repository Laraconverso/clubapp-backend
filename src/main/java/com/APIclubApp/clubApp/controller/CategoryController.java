package com.APIclubApp.clubApp.controller;

import com.APIclubApp.clubApp.model.Category;
import com.APIclubApp.clubApp.model.Employee;
import com.APIclubApp.clubApp.service.CategoryService;
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

    @GetMapping("/list")
    public ResponseEntity<List<Category>> listAllCategories(){
        return ResponseEntity.ok(categoryService.listAllCategories());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id){

        Category category = categoryService.getCategoryById(id);
        if (category != null) {
            return ResponseEntity.ok(category);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/save")
    public ResponseEntity<Category> saveCategory(@RequestBody Category category){
        return ResponseEntity.ok(categoryService.saveCategory(category));
    }

    /*@PutMapping("/update/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody Category category){
        ResponseEntity<Category> response;
        if (categoryService.getCategoryById(id) != null){
            category.setId(id);
            response = ResponseEntity.ok(categoryService.saveCategory(category));
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }*/

    @PutMapping("/update")
    @PermitAll
    public ResponseEntity<Category> updateCategory(@RequestBody Category category){
        ResponseEntity<Category> response;
        if (category.getCategoryId() != null && categoryService.getCategoryById(category.getCategoryId()) != null){
            response = ResponseEntity.ok(categoryService.saveCategory(category));
        }else{
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }
//andre
    /*@PutMapping
    public ResponseEntity<?> editarCategoria(@RequestBody CategoryDTO category) throws ResourceNotFoundException {
        Optional<CategoryDTO> categoriaBuscada= Optional.ofNullable(categoryService.buscarCategoria(category.getId()));
        if (categoriaBuscada.isPresent()){
            logger.info("Se edito la categoria");
            categoryService.editarCategoria(category);
            return ResponseEntity.ok(HttpStatus.OK);
        }
        else {
            throw new Reso*/

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id){
        categoryService.deleteCategory(id);
        return ResponseEntity.ok().body("Deleted");
    }
}


